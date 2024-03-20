package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.in;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.RetornoPagamentoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoSQSINAdapter;
import br.com.api.pedido.valhalla.kitchen.core.applications.services.PedidoService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PedidoPagamentoAprovado implements PedidoSQSINAdapter {

    private final PedidoService pedidoService;

    public PedidoPagamentoAprovado(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    @SqsListener("${queue.in.retorno-pagamento}")
    public void receive(Message<RetornoPagamentoForm> message) {
        pedidoService.processarRetornoPagamento(message.getPayload());
    }
}
