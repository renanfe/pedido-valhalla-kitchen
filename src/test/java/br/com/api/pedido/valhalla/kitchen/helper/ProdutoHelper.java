package br.com.api.pedido.valhalla.kitchen.helper;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ProdutoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ProdutoForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoHelper {

    public static List<Produto> gerarListaProdutos () throws IOException {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(ConverterFilesHelper.convertJsonToObject("produto-lanche-um.json", Produto.class));
        produtos.add(ConverterFilesHelper.convertJsonToObject("produto-lanche-dois.json", Produto.class));

        return produtos;
    }

    public static List<ProdutoEntity> gerarListaProdutosEntity () throws IOException {
        List<ProdutoEntity> produtos = new ArrayList<>();
        produtos.add(ConverterFilesHelper.convertJsonToObject("produto-entity-lanche-um.json", ProdutoEntity.class));
        produtos.add(ConverterFilesHelper.convertJsonToObject("produto-entity-lanche-dois.json", ProdutoEntity.class));

        return produtos;
    }

    public static Produto gerarProduto() throws IOException {
        return ConverterFilesHelper.convertJsonToObject("produto-lanche-um.json", Produto.class);
    }

    public static ProdutoForm gerarProdutoForm() throws IOException {
        return ConverterFilesHelper.convertJsonToObject("produto-form.json", ProdutoForm.class);
    }

    public static Produto gerarProdutoRequest() throws IOException {
        return ConverterFilesHelper.convertJsonToObject("produto-form.json", Produto.class);
    }

    public static ProdutoEntity gerarProdutoEntity () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("produto-entity-lanche-um.json", ProdutoEntity.class);
    }

    public static ProdutoEntity gerarProdutoEntityRequest () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("produto-entity-request.json", ProdutoEntity.class);
    }

}