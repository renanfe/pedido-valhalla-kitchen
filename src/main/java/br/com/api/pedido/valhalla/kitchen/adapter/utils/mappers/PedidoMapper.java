package br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.PedidoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.out.dto.PedidoConfirmado;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;

public class PedidoMapper {
    private PedidoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PedidoEntity pedidoToEntity(final Pedido pedido) {
        return PedidoEntity.builder().clienteId(pedido.getClienteId())
                .status(pedido.getStatus())
                .statusPagamento(pedido.getStatusPagamento())
                .id(pedido.getId()).build();
    }

    public static Pedido pedidoEntityToPedido(final PedidoEntity pedidoEntity) {
        return Pedido.builder().id(pedidoEntity.getId())
                .clienteId(pedidoEntity.getClienteId())
                .status(pedidoEntity.getStatus())
                .statusPagamento(pedidoEntity.getStatusPagamento()).build();
    }

    public static Pedido pedidoFormToPedido(final PedidoForm pedidoForm) {
        return Pedido.builder()
                .clienteId(pedidoForm.getClienteId())
                .produtos(PedidoProdutoMapper
                        .pedidoProdutoFormToPedidoProduto(pedidoForm.getProdutosPedido())).build();
    }


    public static Object pedidoToPedidoConfirmado(final Pedido pedidoAtualizado) {
        return PedidoConfirmado.builder().pedidoId(pedidoAtualizado.getId()).build();
    }
}
