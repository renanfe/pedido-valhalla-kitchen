package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.out.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoConfirmado {
    private Long pedidoId;
}
