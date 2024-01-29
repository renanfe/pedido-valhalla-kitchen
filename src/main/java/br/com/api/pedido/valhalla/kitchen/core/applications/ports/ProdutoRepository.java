package br.com.api.pedido.valhalla.kitchen.core.applications.ports;

import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    List<Produto> buscarTodosProdutos();

    List<Produto> buscarTodosProdutosPorCategoria(final String categoria);

    Optional<Produto> buscarProdutoPorId(final Long id);

    Produto salvarProduto(final Produto produto);
}
