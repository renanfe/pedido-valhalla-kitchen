package br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoProdutoForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.PedidoProduto;

import java.util.ArrayList;
import java.util.List;

public class PedidoProdutoMapper {

    private PedidoProdutoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<PedidoProduto> pedidoProdutoFormToPedidoProduto(List<PedidoProdutoForm> pedidoProdutosForm) {
        List<PedidoProduto> pedidoProdutos = new ArrayList<>();
        for (PedidoProdutoForm pedidoProdutoForm : pedidoProdutosForm) {
            pedidoProdutos.add(PedidoProduto.builder()
                    .produtoId(pedidoProdutoForm.getProdutoId())
                    .quantidade(pedidoProdutoForm.getQuantidade()).build());
        }
        return pedidoProdutos;
    }

}
