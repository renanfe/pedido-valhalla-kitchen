package br.com.api.pedido.valhalla.kitchen.adapter.driver;

import br.com.api.pedido.valhalla.kitchen.core.applications.services.PedidoService;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import br.com.api.pedido.valhalla.kitchen.helper.PedidoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        PedidoController underTest = new PedidoController(pedidoService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void deveRetornarPedido_quandoIdInformadoCorreto() throws Exception {
        // given
        Long id = 1L;
        Pedido pedidoExpected = PedidoHelper.gerarPedido();

        when(pedidoService.buscarPedidoPorId(id)).thenReturn(Optional.of(pedidoExpected));

        // when
        mockMvc.perform(get("/v1/pedidos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // then
        verify(pedidoService, times(1)).buscarPedidoPorId(id);
    }

    @Test
    void deveRetornarVazio_quandoIdNaoEstiverCorreto() throws Exception {
        // given
        Long id = 1L;

        when(pedidoService.buscarPedidoPorId(id)).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/v1/pedidos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        // then
        verify(pedidoService, times(1)).buscarPedidoPorId(id);
    }


}
