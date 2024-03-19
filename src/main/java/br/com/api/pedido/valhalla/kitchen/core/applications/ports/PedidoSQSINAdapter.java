package br.com.api.pedido.valhalla.kitchen.core.applications.ports;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

public interface PedidoSQSINAdapter {

    void receive(@Payload Message<Object> message);

}