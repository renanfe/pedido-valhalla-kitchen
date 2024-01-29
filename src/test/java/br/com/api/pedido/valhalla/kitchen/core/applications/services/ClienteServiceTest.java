package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ClienteForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.ClienteRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;
import br.com.api.pedido.valhalla.kitchen.helper.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    private ClienteService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClienteService(clienteRepository);
    }

    @Test
    void deveRetornarCliente_quandoCpfEstiverCadastrado() throws IOException {
        // given
        String cpf = "12345678958";
        Cliente clienteExpected = ClienteHelper.gerarCliente();

        when(clienteRepository.buscarClientePorCpf(cpf)).thenReturn(Optional.of(clienteExpected));

        // when
        Optional<Cliente> clienteAtual = underTest.buscaClienteCpf(cpf);

        // then
        verify(clienteRepository, times(1)).buscarClientePorCpf(cpf);
        assertTrue(clienteAtual.isPresent());
        assertEquals(clienteAtual.get().getId(), clienteExpected.getId());
        assertEquals(clienteAtual.get().getCpf(), clienteExpected.getCpf());
        assertEquals(clienteAtual.get().getEmail(), clienteExpected.getEmail());
        assertEquals(clienteAtual.get().getNome(), clienteExpected.getNome());
    }

    @Test
    void deveRetornarEmpty_quandoCpfNaoEstiverCadastrado() {
        // given
        String cpf = "12345678958";

        when(clienteRepository.buscarClientePorCpf(cpf)).thenReturn(Optional.empty());

        // when
        Optional<Cliente> clienteAtual = underTest.buscaClienteCpf(cpf);

        // then
        verify(clienteRepository, times(1)).buscarClientePorCpf(cpf);
        assertFalse(clienteAtual.isPresent());
    }

    @Test
    void deveCriarCliente_quandoTodosOsDadosForemInformados() throws IOException {
        //given
        ClienteForm clienteForm = ClienteHelper.gerarClienteForm();
        Cliente clienteExpected = ClienteHelper.gerarCliente();
        Cliente clienteRequest = clienteExpected;
        clienteRequest.setId(null);

        when(clienteRepository.criarCliente(clienteRequest)).thenReturn(clienteExpected);

        //when
        Cliente clienteAtual = underTest.criarCliente(clienteForm);

        //then
        verify(clienteRepository, times(1)).criarCliente(any(Cliente.class));
        assertNotNull(clienteAtual);
        assertEquals(clienteAtual.getId(), clienteExpected.getId());
        assertEquals(clienteAtual.getCpf(), clienteExpected.getCpf());
        assertEquals(clienteAtual.getEmail(), clienteExpected.getEmail());
        assertEquals(clienteAtual.getNome(), clienteExpected.getNome());

    }

}
