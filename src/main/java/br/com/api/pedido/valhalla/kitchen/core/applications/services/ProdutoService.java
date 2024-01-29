package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ProdutoForm;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.ProdutoMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.ProdutoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(final ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.buscarTodosProdutos();
    }

    public List<Produto> buscarTodosProdutosPorCategoria(final String categoria) {
        return produtoRepository.buscarTodosProdutosPorCategoria(categoria);
    }

    public Optional<Produto> buscarProdutoPorId(final Long id) {
        return produtoRepository.buscarProdutoPorId(id);
    }

    public Produto criarProduto(final ProdutoForm produtoForm) {
        return produtoRepository.salvarProduto(ProdutoMapper.produtoFormToProduto(produtoForm));
    }

    public Optional<Produto> editarProdutoPorId(final Long id, final ProdutoForm produtoForm) {
        Optional<Produto> produtoDTO = produtoRepository.buscarProdutoPorId(id);

        if(produtoDTO.isPresent()) {
            Produto produtoAtualizado = ProdutoMapper.produtoFormToProduto(produtoForm);
            produtoAtualizado.setId(id);

            produtoRepository.salvarProduto(produtoAtualizado);
        }

        return produtoDTO;
    }

    public Optional<Produto> inativarProduto(final Long id) {
        Optional<Produto> produtoDTO = produtoRepository.buscarProdutoPorId(id);

        if (produtoDTO.isPresent()) {
            Produto produtoAtualizado = produtoDTO.get();
            produtoAtualizado.setStatus(Status.INATIVO.getDescricao());

            produtoRepository.salvarProduto(produtoAtualizado);

            return Optional.of(produtoAtualizado);
        }

        return produtoDTO;
    }
}
