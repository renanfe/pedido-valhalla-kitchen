package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.messaging.in;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.AtualizacaoPedidoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.RetornoPagamentoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.AtualizacaoPedidoSQSINAdapter;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.RetornoPagamentoSQSINAdapter;
import br.com.api.pedido.valhalla.kitchen.core.applications.services.PedidoService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class AtualizacaoPedido implements AtualizacaoPedidoSQSINAdapter {

    private final PedidoService pedidoService;

    public AtualizacaoPedido(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @Override
    @SqsListener("${queue.in.atualizacao-pedido}")
    public void receive(Message<AtualizacaoPedidoForm> message) {
        pedidoService.atualizarSituacaoPedido(message.getPayload());
    }
}
