package br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ProdutoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ProdutoForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Categoria;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Status;

public class ProdutoMapper {

    private ProdutoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProdutoEntity produtoToEntity(final Produto produto) {
        return ProdutoEntity.builder().nome(produto.getNome())
                .descricao(produto.getDescricao())
                .categoria(Categoria.getByDescricao(produto.getCategoria()).getId())
                .preco(produto.getPreco())
                .status(Status.getByDescricao(produto.getStatus()).getId()).build();
    }

    public static Produto produtoEntityToProduto(final ProdutoEntity produtoEntity) {
        return Produto.builder().id(produtoEntity.getId())
                .categoria(Categoria.getById(produtoEntity.getCategoria()).getDescricao())
                .preco(produtoEntity.getPreco())
                .descricao(produtoEntity.getDescricao())
                .nome(produtoEntity.getNome())
                .status(Status.getById(produtoEntity.getStatus()).getDescricao()).build();
    }

    public static Produto produtoFormToProduto(final ProdutoForm produtoForm) {
        return Produto.builder().categoria(produtoForm.getCategoria())
                .descricao(produtoForm.getDescricao())
                .preco(produtoForm.getPreco())
                .nome(produtoForm.getNome())
                .status(produtoForm.getStatus()).build();
    }

}
