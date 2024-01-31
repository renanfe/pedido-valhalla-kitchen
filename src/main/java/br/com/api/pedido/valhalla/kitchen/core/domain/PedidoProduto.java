package br.com.api.pedido.valhalla.kitchen.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoProduto {
    private Long produtoId;
    private int quantidade;
}
