package br.com.api.pedido.valhalla.kitchen.adapter.driver.form;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoForm {
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String categoria;
    private String status;
}
