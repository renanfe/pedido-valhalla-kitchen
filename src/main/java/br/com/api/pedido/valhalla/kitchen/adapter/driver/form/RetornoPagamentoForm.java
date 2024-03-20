package br.com.api.pedido.valhalla.kitchen.adapter.driver.form;

import lombok.Getter;

@Getter
public class RetornoPagamentoForm {
    private Long pedidoId;
    private String statusRetorno;
    private String motivo;
}
