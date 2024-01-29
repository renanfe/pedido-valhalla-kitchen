package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.PedidoMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> buscarTodosPedidos() {
        return pedidoRepository.buscarTodosPedidos();
    }

    public Optional<Pedido> buscarPedidoPorId(final Long id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    public Pedido criarPedido(final PedidoForm pedidoForm) {
        return pedidoRepository.salvarPedido(PedidoMapper.pedidoFormToPedido(pedidoForm));
    }

    public Optional<Pedido> alterarStatusPedido(final Long id) {
        Optional<Pedido> pedido = pedidoRepository.buscarPedidoPorId(id);

        if (pedido.isPresent()) {
            Pedido pedidoAtualizado = atualizarParaProximoStatus(pedido.get(), pedido.get().getStatus());
            pedidoRepository.salvarPedido(pedidoAtualizado);

            return Optional.of(pedidoAtualizado);
        }
        return pedido;
    }

    private Pedido atualizarParaProximoStatus(final Pedido pedido, final String status) {
        switch (status) {
            case "Recebido" -> pedido.setStatus("Em preparação");
            case "Em preparação" -> pedido.setStatus("Pronto");
            case "Pronto" -> pedido.setStatus("Retirado");
            case "Retirado" -> pedido.setStatus("Finalizado");
            default -> throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        return pedido;
    }

    public String consultarStatusPagamento(final Long id) {
        return pedidoRepository.buscarPedidoPorId(id).get().getStatusPagamento();
    }

}