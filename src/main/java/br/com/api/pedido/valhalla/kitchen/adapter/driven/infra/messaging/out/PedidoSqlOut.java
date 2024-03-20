package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.out;

import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoSQSOUTAdapter;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoSqlOut implements PedidoSQSOUTAdapter {

    private final SqsTemplate sqsTemplate;

    public PedidoSqlOut(final SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    @Override
    public void sendToQueue(Object message, String queueName) {
        sqsTemplate.send(sqsSendOptions -> sqsSendOptions
                                .queue(queueName)
                                .payload(message));
    }
}