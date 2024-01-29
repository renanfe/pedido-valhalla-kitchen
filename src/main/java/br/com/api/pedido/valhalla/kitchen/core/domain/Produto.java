package br.com.api.pedido.valhalla.kitchen.core.domain;

import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String categoria;

    @Builder.Default
    private String status = Status.ATIVO.getDescricao();
}
