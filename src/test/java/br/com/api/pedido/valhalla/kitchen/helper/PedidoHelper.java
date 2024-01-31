package br.com.api.pedido.valhalla.kitchen.helper;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.PedidoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PedidoHelper {

    public static List<Pedido> gerarListaPedidos () throws IOException {
        List<Pedido> produtos = new ArrayList<>();
        produtos.add(ConverterFilesHelper.convertJsonToObject("pedido-completo.json", Pedido.class));
        return produtos;
    }

    public static Pedido gerarPedido () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("pedido-completo.json", Pedido.class);
    }

    public static PedidoForm gerarPedidoForm () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("pedido-form.json", PedidoForm.class);
    }

    public static Pedido gerarPedidoRequest () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("pedido-request.json", Pedido.class);
    }

    public static List<PedidoEntity> gerarListaPedidosEntity () throws IOException {
        List<PedidoEntity> produtos = new ArrayList<>();
        produtos.add(ConverterFilesHelper.convertJsonToObject("pedido-completo.json", PedidoEntity.class));
        return produtos;
    }

    public static PedidoEntity gerarPedidoEntity () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("pedido-completo.json", PedidoEntity.class);
    }

    public static PedidoEntity gerarPedidoEntityRequest () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("pedido-entity-request.json", PedidoEntity.class);
    }

}
