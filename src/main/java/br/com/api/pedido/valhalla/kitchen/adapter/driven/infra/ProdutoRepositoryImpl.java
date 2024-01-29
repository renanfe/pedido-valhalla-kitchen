package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ProdutoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa.ProdutoRepositoryJpa;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.ProdutoMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.ProdutoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoRepositoryJpa produtoRepositoryJpa;

    public ProdutoRepositoryImpl(final ProdutoRepositoryJpa produtoRepositoryJpa) {
        this.produtoRepositoryJpa = produtoRepositoryJpa;
    }

    @Override
    public List<Produto> buscarTodosProdutos() {
        return converterListEntityToListDto(produtoRepositoryJpa.findAll());
    }

    @Override
    public List<Produto> buscarTodosProdutosPorCategoria(final String categoria) {
        return converterListEntityToListDto(produtoRepositoryJpa.findAllByCategoria(Categoria.getByDescricao(categoria).getId()));
    }

    private List<Produto> converterListEntityToListDto(final List<ProdutoEntity> produtoEntities) {
        List<Produto> produtos = new ArrayList<>();

        for (ProdutoEntity produto : produtoEntities) {
            produtos.add(ProdutoMapper.produtoEntityToProduto(produto));
        }
        return produtos;
    }

    @Override
    public Optional<Produto> buscarProdutoPorId(final Long id) {
        return produtoRepositoryJpa.findById(id).map(ProdutoMapper::produtoEntityToProduto);
    }

    @Override
    public Produto salvarProduto(final Produto produto) {
        ProdutoEntity produtoEntity = produtoRepositoryJpa.save(ProdutoMapper.produtoToEntity(produto));
        return ProdutoMapper.produtoEntityToProduto(produtoEntity);
    }
}
