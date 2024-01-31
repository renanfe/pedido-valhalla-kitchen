package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.PedidoMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import org.springframework.stereotype.Service;

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

}