package br.com.api.pedido.valhalla.kitchen.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
    private Long id;
    private UUID clienteId;

    @Builder.Default
    private String status = "Recebido";
    private List<PedidoProduto> produtos;
    @Builder.Default
    private String statusPagamento = "Aguardando";
}