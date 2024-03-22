package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.in;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.RetornoPagamentoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.RetornoPagamentoSQSINAdapter;
import br.com.api.pedido.valhalla.kitchen.core.applications.services.PedidoService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class RetornoPagamento implements RetornoPagamentoSQSINAdapter {

    private final PedidoService pedidoService;

    public RetornoPagamento(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    @SqsListener("${queue.in.retorno-pagamento}")
    public void receive(Message<RetornoPagamentoForm> message) {
        pedidoService.processarRetornoPagamento(message.getPayload());
    }
}
