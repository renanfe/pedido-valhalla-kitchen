package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.AtualizacaoPedidoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.RetornoPagamentoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.PedidoMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoRepository;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoSQSOUTAdapter;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PedidoService {

    @Value("${queue.pedido-gerado}")
    private String pedidoGeradoQueue;

    @Value("${queue.pedido-aprovado}")
    private String pedidoAprovadoQueue;

    private final PedidoRepository pedidoRepository;

    private final PedidoSQSOUTAdapter pedidoSQSOUTAdapter;

    public PedidoService(final PedidoRepository pedidoRepository, final PedidoSQSOUTAdapter pedidoSQSOUTAdapter) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoSQSOUTAdapter = pedidoSQSOUTAdapter;
    }

    public List<Pedido> buscarTodosPedidos() {
        return pedidoRepository.buscarTodosPedidos();
    }

    public Optional<Pedido> buscarPedidoPorId(final Long id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    @Transactional
    public Pedido criarPedido(final PedidoForm pedidoForm) {
        Pedido pedidoSalvo = pedidoRepository.salvarPedido(PedidoMapper.pedidoFormToPedido(pedidoForm));

        pedidoSQSOUTAdapter.sendToQueue(pedidoSalvo.getId(), pedidoGeradoQueue);

        return pedidoSalvo;
    }

    @Transactional
    public void processarRetornoPagamento (RetornoPagamentoForm retornoPagamentoForm) {
        if(retornoPagamentoForm.getStatusRetorno().equals("CONCLUIDO")) {
            processarPagamentoAprovado(retornoPagamentoForm);
        } else {
            processarPagamentoRejeitado(retornoPagamentoForm);
        }
    }

    private void processarPagamentoRejeitado(RetornoPagamentoForm retornoPagamentoForm) {

        Optional<Pedido> pedido = pedidoRepository.buscarPedidoPorId(retornoPagamentoForm.getPedidoId());

        if (pedido.isPresent()) {
            pedidoRepository.salvarPedido(Pedido.atualizarPagamento(pedido.get(), retornoPagamentoForm.getStatusRetorno()));

            enviaComunicacaoUsuario(retornoPagamentoForm);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }

    }

    private void enviaComunicacaoUsuario(RetornoPagamentoForm retornoPagamentoForm) {
        log.info("Seu pedido número: " + retornoPagamentoForm.getPedidoId() + " teve um problema no pagamento, tendo este motivo: " + retornoPagamentoForm.getMotivo());
    }

    private void processarPagamentoAprovado(RetornoPagamentoForm retornoPagamentoForm) {

        Optional<Pedido> pedido = pedidoRepository.buscarPedidoPorId(retornoPagamentoForm.getPedidoId());

        if (pedido.isPresent()) {
            Pedido pedidoAtualizado = pedidoRepository.salvarPedido(Pedido.atualizarPagamento(pedido.get(), retornoPagamentoForm.getStatusRetorno()));

            pedidoSQSOUTAdapter.sendToQueue(PedidoMapper.pedidoToPedidoConfirmado(pedidoAtualizado), pedidoAprovadoQueue);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }

    }

    public void atualizarSituacaoPedido(AtualizacaoPedidoForm atualizacaoPedidoForm) {
        Optional<Pedido> pedido = pedidoRepository.buscarPedidoPorId(atualizacaoPedidoForm.getPedidoId());

        if (pedido.isPresent()) {
            pedidoRepository.salvarPedido(Pedido.atualizarStatus(pedido.get(), atualizacaoPedidoForm.getStatus()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
    }
}