package br.com.api.pedido.valhalla.kitchen.adapter.driver;

import br.com.api.pedido.valhalla.kitchen.core.applications.services.ClienteService;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;
import br.com.api.pedido.valhalla.kitchen.helper.ClienteHelper;
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
class ClienteControllerTest {
    @Mock
    private ClienteService clienteService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ClienteController underTest = new ClienteController(clienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }
    @Test
    void deveRetornarCliente_quandoCpfEstiverCadastrado() throws Exception {
        // given
        String cpf = "12345678958";
        Cliente clienteExpected = ClienteHelper.gerarClienteRetornoTela();

        when(clienteService.buscaClienteCpf(cpf)).thenReturn(Optional.of(clienteExpected));

        // when
        mockMvc.perform(get("/v1/clientes/{cpf}", cpf)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // then
        verify(clienteService, times(1)).buscaClienteCpf(cpf);
    }

    @Test
    void deveRetornarEmpty_quandoCpfNaoEstiverCadastrado() throws Exception {
        // given
        String cpf = "12345678958";

        when(clienteService.buscaClienteCpf(cpf)).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/v1/clientes/{cpf}", cpf)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        // then
        verify(clienteService, times(1)).buscaClienteCpf(cpf);
    }

    /*@Test
    public void quandoBuscoUmPagamentoComId_EntaoRetornaAsInformacoesPagamento() throws Exception {
        Long id = PagamentoHelper.gerarLong();
        Optional<Pagamento> pagamento = Optional.of(PagamentoHelper.buildPagamento());
        when(pagamentoService.buscarPagamentoPorId(any(Long.class))).thenReturn(pagamento);
        mvc.perform(
                        get("/v1/pagamentos/{id}", id)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(pagamentoService, times(1)).buscarPagamentoPorId(any(Long.class));

    }

    @Test
    public void quandoCanceloUmPagamento_entaoRetornaPagamentoComStatusCancelado() throws Exception {
        Long id = PagamentoHelper.gerarLong();
        Optional<Pagamento> pagamento = Optional.of(PagamentoHelper.buildPagamento());
        when(pagamentoService.cancelarPagamento(any(Long.class))).thenReturn(pagamento);
        mvc.perform(
                        delete("/v1/pagamentos/{id}", id)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(pagamentoService, times(1)).cancelarPagamento(any(Long.class));

    }*/
}
