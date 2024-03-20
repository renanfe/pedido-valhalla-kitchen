package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.in;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.RetornoPagamentoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoSQSINAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PedidoAtualizacaoSituacao implements PedidoSQSINAdapter {
    @Override
    public void receive(Message<RetornoPagamentoForm> message) {
        // TO DO
        // Chama método lógica da aprovação
    }
}
