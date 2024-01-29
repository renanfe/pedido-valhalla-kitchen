package br.com.api.pedido.valhalla.kitchen.core.domain;

import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
