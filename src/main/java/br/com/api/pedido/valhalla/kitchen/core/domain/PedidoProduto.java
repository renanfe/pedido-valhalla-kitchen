package br.com.api.pedido.valhalla.kitchen.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoProduto {
    private Long produtoId;
    private int quantidade;
}
