package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.PedidoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa.PedidoRepositoryJpa;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoRepositoryImplTest {

    @Mock
    private PedidoRepositoryJpa pedidoRepositoryJpa;

    private PedidoRepositoryImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new PedidoRepositoryImpl(pedidoRepositoryJpa);
    }

    @Test
    void deveRetornarUmaListaDePedidos_quandoEstiveremCadastrados() throws IOException {
        // given
        List<PedidoEntity> pedidosEntity = PedidoHelper.gerarListaPedidosEntity();

        when(pedidoRepositoryJpa.findAll()).thenReturn(pedidosEntity);

        // when
        List<Pedido> pedidosAtual = underTest.buscarTodosPedidos();

        // then
        verify(pedidoRepositoryJpa, times(1)).findAll();
        assertEquals(1, pedidosAtual.size());
        assertEquals(pedidosEntity.get(0).getClienteId(), pedidosAtual.get(0).getClienteId());
    }

    @Test
    void deveRetornarPedido_quandoIdInformadoCorreto() throws IOException {
        // given
        Long id = 1L;
        PedidoEntity pedidoExpected = PedidoHelper.gerarPedidoEntity();

        when(pedidoRepositoryJpa.findById(id)).thenReturn(Optional.of(pedidoExpected));

        // when
        Optional<Pedido> pedidoAtual = underTest.buscarPedidoPorId(id);

        // then
        verify(pedidoRepositoryJpa, times(1)).findById(id);
        assertTrue(pedidoAtual.isPresent());
        assertEquals(pedidoExpected.getId(), pedidoAtual.get().getId());
        assertEquals(pedidoExpected.getClienteId(), pedidoAtual.get().getClienteId());
    }

    @Test
    void deveCriarPedido_quandoTodosCamposInformadoCorreto() throws IOException {
        // given
        PedidoEntity pedidoExpected = PedidoHelper.gerarPedidoEntity();
        Pedido pedidoRequest = PedidoHelper.gerarPedidoRequest();

        when(pedidoRepositoryJpa.save(any(PedidoEntity.class))).thenReturn(pedidoExpected);

        // when
        Pedido pedidoAtual = underTest.salvarPedido(pedidoRequest);

        // then
        verify(pedidoRepositoryJpa, times(1)).save(any(PedidoEntity.class));
        assertNotNull(pedidoAtual);
        assertEquals(pedidoExpected.getClienteId(), pedidoAtual.getClienteId());
    }
}
