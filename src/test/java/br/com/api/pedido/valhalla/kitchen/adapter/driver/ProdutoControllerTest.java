package br.com.api.pedido.valhalla.kitchen.adapter.driver;

import br.com.api.pedido.valhalla.kitchen.core.applications.services.ProdutoService;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import br.com.api.pedido.valhalla.kitchen.helper.ProdutoHelper;
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
class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ProdutoController underTest = new ProdutoController(produtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void deveRetornarProduto_quandoIdInformadoCorreto() throws Exception {
        // given
        Long id = 10L;
        Produto produtoExpected = ProdutoHelper.gerarProduto();

        when(produtoService.buscarProdutoPorId(id)).thenReturn(Optional.of(produtoExpected));

        // when
        mockMvc.perform(get("/v1/produtos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // then
        verify(produtoService, times(1)).buscarProdutoPorId(id);
    }

    @Test
    void deveRetornarVazio_quandoIdNaoEstiverCorreto() throws Exception {
        // given
        Long id = 10L;

        when(produtoService.buscarProdutoPorId(id)).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/v1/produtos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        // then
        verify(produtoService, times(1)).buscarProdutoPorId(id);
    }
}
