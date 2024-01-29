package br.com.api.pedido.valhalla.kitchen.adapter.driver.form;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class PedidoForm {
    private UUID clienteId;
    private Boolean isAnonimo;
    private List<PedidoProdutoForm> produtosPedido;
}
