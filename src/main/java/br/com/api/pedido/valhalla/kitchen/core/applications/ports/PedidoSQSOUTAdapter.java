package br.com.api.pedido.valhalla.kitchen.core.applications.ports;

public interface PedidoSQSOUTAdapter {

    void sendToQueue(Object message, String queueName);

}
