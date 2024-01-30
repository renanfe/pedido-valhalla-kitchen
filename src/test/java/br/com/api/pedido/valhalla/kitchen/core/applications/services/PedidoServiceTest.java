package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import br.com.api.pedido.valhalla.kitchen.helper.PedidoHelper;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    private PedidoService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PedidoService(pedidoRepository);
    }

    @Test
    void deveRetornarUmaListaDePedidos_quandoEstiveremCadastrados() throws IOException {
        // given
        List<Pedido> pedidos = PedidoHelper.gerarListaPedidos();
        
        when(pedidoRepository.buscarTodosPedidos()).thenReturn(pedidos);
        
        // when
        List<Pedido> pedidosAtual = underTest.buscarTodosPedidos();

        // then
        verify(pedidoRepository, times(1)).buscarTodosPedidos();
        assertEquals(1, pedidosAtual.size());
        assertEquals(pedidos.get(0).getClienteId(), pedidosAtual.get(0).getClienteId());
    }

    @Test
    void deveRetornarPedido_quandoIdInformadoCorreto() throws IOException {
        // given
        Long id = 1L;
        Pedido pedidoExpected = PedidoHelper.gerarPedido();

        when(pedidoRepository.buscarPedidoPorId(id)).thenReturn(Optional.of(pedidoExpected));

        // when
        Optional<Pedido> pedidoAtual = underTest.buscarPedidoPorId(id);

        // then
        verify(pedidoRepository, times(1)).buscarPedidoPorId(id);
        assertTrue(pedidoAtual.isPresent());
        assertEquals(id, pedidoAtual.get().getId());
        assertEquals(2, pedidoAtual.get().getProdutos().size());
        assertEquals(pedidoExpected.getClienteId(), pedidoAtual.get().getClienteId());
    }

    @Test
    void deveCriarPedido_quandoTodosCamposInformadoCorreto() throws IOException {
        // given
        PedidoForm pedidoForm = PedidoHelper.gerarPedidoForm();
        Pedido pedido = PedidoHelper.gerarPedido();
        Pedido pedidoRequest = PedidoHelper.gerarPedidoRequest();

        when(pedidoRepository.salvarPedido(pedidoRequest)).thenReturn(pedido);
        
        // when
        Pedido pedidoAtual = underTest.criarPedido(pedidoForm);

        // then
        verify(pedidoRepository, times(1)).salvarPedido(pedidoRequest);
        assertNotNull(pedidoAtual);
        assertEquals(pedidoRequest.getClienteId(), pedidoAtual.getClienteId());
    }



}
