package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ProdutoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa.ProdutoRepositoryJpa;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Categoria;
import br.com.api.pedido.valhalla.kitchen.helper.ProdutoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoRepositoryImplTest {

    @Mock
    private ProdutoRepositoryJpa produtoRepositoryJpa;

    private ProdutoRepositoryImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProdutoRepositoryImpl(produtoRepositoryJpa);
    }

    @Test
    void deveRetornarUmaListaDeProdutos_quandoEstiveremCadastrados() throws IOException {
        // given
        List<ProdutoEntity> produtosEntity = ProdutoHelper.gerarListaProdutosEntity();

        when(produtoRepositoryJpa.findAll()).thenReturn(produtosEntity);

        // when
        List<Produto> produtosAtual = underTest.buscarTodosProdutos();

        // then
        verify(produtoRepositoryJpa, times(1)).findAll();
        assertEquals(2, produtosAtual.size());
    }
    
    @Test
    void deveRetornarUmaListaDeProdutosDaMesmaCategoria() throws IOException {
        // given
        int categoriaId = 1;
        String categoriaDescricao = "Lanche";
        List<ProdutoEntity> produtosEntity = ProdutoHelper.gerarListaProdutosEntity();
        
        when(produtoRepositoryJpa.findAllByCategoria(categoriaId)).thenReturn(produtosEntity);

        // when
        List<Produto> produtosAtual = underTest.buscarTodosProdutosPorCategoria(categoriaDescricao);

        // then
        verify(produtoRepositoryJpa, times(1)).findAllByCategoria(categoriaId);
        assertEquals(2, produtosAtual.size());
        assertEquals(produtosAtual.get(0).getCategoria(), categoriaDescricao);
        assertEquals(produtosAtual.get(1).getCategoria(), categoriaDescricao);
    }

    @Test
    void deveRetornarProduto_quandoIdInformadoCorreto() throws IOException {
        // given
        Long id = 10L;
        ProdutoEntity produtoEntity = ProdutoHelper.gerarProdutoEntity();

        when(produtoRepositoryJpa.findById(id)).thenReturn(Optional.of(produtoEntity));

        // when
        Optional<Produto> produtoAtual = underTest.buscarProdutoPorId(id);

        // then
        verify(produtoRepositoryJpa, times(1)).findById(id);
        assertTrue(produtoAtual.isPresent());
        assertEquals(produtoEntity.getDescricao(), produtoAtual.get().getDescricao());
        assertEquals(produtoEntity.getNome(), produtoAtual.get().getNome());
        assertEquals(produtoEntity.getPreco(), produtoAtual.get().getPreco());
        assertEquals(Categoria.LANCHE.getDescricao(), produtoAtual.get().getCategoria());
    }

    @Test
    void deveCriarProduto_quandoTodosCamposInformadoCorreto() throws IOException {
        // given
        ProdutoEntity produtoEntity = ProdutoHelper.gerarProdutoEntity();
        Produto produtoRequest = ProdutoHelper.gerarProdutoRequest();

        when(produtoRepositoryJpa.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);

        // when
        Produto produtoAtual = underTest.salvarProduto(produtoRequest);

        // then
        verify(produtoRepositoryJpa, times(1)).save(any(ProdutoEntity.class));
        assertNotNull(produtoAtual);
        assertEquals(produtoEntity.getId(), produtoAtual.getId());
    }

}