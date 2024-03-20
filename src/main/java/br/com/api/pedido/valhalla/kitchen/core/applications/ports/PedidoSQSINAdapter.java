package br.com.api.pedido.valhalla.kitchen.core.applications.ports;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.RetornoPagamentoForm;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

public interface PedidoSQSINAdapter {

    void receive(@Payload Message<RetornoPagamentoForm> message);

}