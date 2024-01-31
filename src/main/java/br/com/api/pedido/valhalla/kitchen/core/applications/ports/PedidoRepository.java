package br.com.api.pedido.valhalla.kitchen.core.applications.ports;

import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    List<Pedido> buscarTodosPedidos();

    Optional<Pedido> buscarPedidoPorId(Long id);

    Pedido salvarPedido(Pedido pedido);

}