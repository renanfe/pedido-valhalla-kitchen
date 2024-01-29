package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ProdutoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.ProdutoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Categoria;
import br.com.api.pedido.valhalla.kitchen.core.domain.enums.Status;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    private ProdutoService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProdutoService(produtoRepository);
    }

    @Test
    void deveRetornarUmaListaDeProdutos_quandoEstiveremCadastrados() throws IOException {
        // given
        List<Produto> produtosExpected = ProdutoHelper.gerarListaProdutos();

        when(produtoRepository.buscarTodosProdutos()).thenReturn(produtosExpected);

        // when
        List<Produto> produtosAtual = underTest.buscarTodosProdutos();

        // then
        verify(produtoRepository, times(1)).buscarTodosProdutos();
        assertEquals(2, produtosAtual.size());
    }

    @Test
    void deveRetornarUmaListaDeProdutosDaMesmaCategoria() throws IOException {
        // given
        List<Produto> produtosExpected = ProdutoHelper.gerarListaProdutos();
        String categoriaBuscada = Categoria.LANCHE.getDescricao();

        when(produtoRepository.buscarTodosProdutosPorCategoria(categoriaBuscada)).thenReturn(produtosExpected);

        // when
        List<Produto> produtosAtual = underTest.buscarTodosProdutosPorCategoria(categoriaBuscada);

        // then
        verify(produtoRepository, times(1)).buscarTodosProdutosPorCategoria(categoriaBuscada);
        assertEquals(2, produtosAtual.size());
    }

    @Test
    void deveRetornarProduto_quandoIdInformadoCorreto() throws IOException {
        // given
        Produto produto = ProdutoHelper.gerarProduto();
        Long id = 10L;

        when(produtoRepository.buscarProdutoPorId(id)).thenReturn(Optional.of(produto));
        
        // when
        Optional<Produto> produtoAtual = underTest.buscarProdutoPorId(id);

        // then
        verify(produtoRepository, times(1)).buscarProdutoPorId(id);
        assertTrue(produtoAtual.isPresent());
        assertEquals(produto.getId(), produtoAtual.get().getId());
        assertEquals(produto.getNome(), produtoAtual.get().getNome());
        assertEquals(produto.getPreco(), produtoAtual.get().getPreco());
        assertEquals(produto.getCategoria(), produtoAtual.get().getCategoria());
        assertEquals(produto.getStatus(), produtoAtual.get().getStatus());
        assertEquals(produto.getDescricao(), produtoAtual.get().getDescricao());
    }

    @Test
    void deveCriarProduto_quandoTodosCamposInformadoCorreto() throws IOException {
        // given
        Produto produtoRequest = ProdutoHelper.gerarProdutoRequest();
        Produto produtoExpected = ProdutoHelper.gerarProduto();
        ProdutoForm produtoForm = ProdutoHelper.gerarProdutoForm();

        when(produtoRepository.salvarProduto(produtoRequest)).thenReturn(produtoExpected);

        // when
        Produto produtoAtual = underTest.criarProduto(produtoForm);

        // then
        verify(produtoRepository, times(1)).salvarProduto(produtoRequest);
        assertEquals(produtoExpected.getId(), produtoAtual.getId());
        assertEquals(produtoExpected.getNome(), produtoAtual.getNome());
        assertEquals(produtoExpected.getPreco(), produtoAtual.getPreco());
        assertEquals(produtoExpected.getCategoria(), produtoAtual.getCategoria());
        assertEquals(produtoExpected.getStatus(), produtoAtual.getStatus());
        assertEquals(produtoExpected.getDescricao(), produtoAtual.getDescricao());
    }

    @Test
    void deveEditarProduto_quandoIdEstiverCorreto() throws IOException {
        // given
        Long id = 10L;
        Produto produtoOriginal = ProdutoHelper.gerarProduto();
        ProdutoForm produtoAlteradoForm = ProdutoHelper.gerarProdutoForm();
        Produto produtoAlterado = ProdutoHelper.gerarProduto();
        produtoAlterado.setNome(produtoAlteradoForm.getNome());
        produtoAlterado.setPreco(produtoAlteradoForm.getPreco());
        produtoAlterado.setStatus(produtoAlteradoForm.getStatus());
        produtoAlterado.setDescricao(produtoAlteradoForm.getDescricao());

        when(produtoRepository.buscarProdutoPorId(id)).thenReturn(Optional.of(produtoOriginal));
        when(produtoRepository.salvarProduto(produtoAlterado)).thenReturn(produtoAlterado);

        // when
        Optional<Produto> produtoAtual = underTest.editarProdutoPorId(id, produtoAlteradoForm);

        // then
        verify(produtoRepository, times(1)).buscarProdutoPorId(id);
        verify(produtoRepository, times(1)).salvarProduto(produtoAlterado);
        assertTrue(produtoAtual.isPresent());
        assertEquals(produtoAlterado.getId(), produtoAtual.get().getId());
        assertEquals(produtoAlterado.getNome(), produtoAtual.get().getNome());
        assertEquals(produtoAlterado.getPreco(), produtoAtual.get().getPreco());
        assertEquals(produtoAlterado.getCategoria(), produtoAtual.get().getCategoria());
        assertEquals(produtoAlterado.getStatus(), produtoAtual.get().getStatus());
        assertEquals(produtoAlterado.getDescricao(), produtoAtual.get().getDescricao());
    }

    @Test
    void deveRetornarVazio_quandoIdNaoEstiverCorretoAoAlterarProduto() throws IOException {
        // given
        Long id = 10L;
        ProdutoForm produtoAlteradoForm = ProdutoHelper.gerarProdutoForm();

        when(produtoRepository.buscarProdutoPorId(id)).thenReturn(Optional.empty());

        // when
        Optional<Produto> produtoAtual = underTest.editarProdutoPorId(id, produtoAlteradoForm);

        // then
        verify(produtoRepository, times(1)).buscarProdutoPorId(id);
        verify(produtoRepository, never()).salvarProduto(any(Produto.class));
        assertFalse(produtoAtual.isPresent());
    }

    @Test
    void deveInativarProduto_quandoIdEstiverCorreto() throws IOException {
        // given
        Long id = 10L;
        Produto produtoOriginal = ProdutoHelper.gerarProduto();
        Produto produtoAlterado = ProdutoHelper.gerarProduto();
        produtoAlterado.setStatus(Status.INATIVO.getDescricao());

        when(produtoRepository.buscarProdutoPorId(id)).thenReturn(Optional.of(produtoOriginal));
        when(produtoRepository.salvarProduto(produtoAlterado)).thenReturn(produtoAlterado);

        // when
        Optional<Produto> produtoAtual = underTest.inativarProduto(id);

        // then
        verify(produtoRepository, times(1)).buscarProdutoPorId(id);
        verify(produtoRepository, times(1)).salvarProduto(produtoAlterado);
        assertTrue(produtoAtual.isPresent());
        assertEquals(produtoAlterado.getId(), produtoAtual.get().getId());
        assertEquals(produtoAlterado.getNome(), produtoAtual.get().getNome());
        assertEquals(produtoAlterado.getPreco(), produtoAtual.get().getPreco());
        assertEquals(produtoAlterado.getCategoria(), produtoAtual.get().getCategoria());
        assertEquals(produtoAlterado.getStatus(), produtoAtual.get().getStatus());
        assertEquals(produtoAlterado.getDescricao(), produtoAtual.get().getDescricao());
    }

    @Test
    void deveRetornarVazio_quandoIdNaoEstiverCorretoAoInativarProduto() throws IOException {
        // given
        Long id = 10L;

        when(produtoRepository.buscarProdutoPorId(id)).thenReturn(Optional.empty());

        // when
        Optional<Produto> produtoAtual = underTest.inativarProduto(id);

        // then
        verify(produtoRepository, times(1)).buscarProdutoPorId(id);
        verify(produtoRepository, never()).salvarProduto(any(Produto.class));
        assertFalse(produtoAtual.isPresent());
    }

}
