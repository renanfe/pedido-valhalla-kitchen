package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ClienteEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa.ClienteRepositoryJpa;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteRepositoryImplTest {

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    private ClienteRepositoryImpl underTest;

    @BeforeEach
    void setUp () {
        underTest = new ClienteRepositoryImpl(clienteRepositoryJpa);
    }

    @Test
    void deveRetornarCliente_quandoCpfEstiverCadastrado() throws IOException {
        // given
        String cpf = "12345678958";
        ClienteEntity clienteEntity = ClienteHelper.gerarClienteEntity();
        Cliente clienteExpected = ClienteHelper.gerarClienteRetornoTela();

        when(clienteRepositoryJpa.getByCpf(cpf)).thenReturn(Optional.of(clienteEntity));

        // when
        Optional<Cliente> clienteAtual = underTest.buscarClientePorCpf(cpf);

        // then
        verify(clienteRepositoryJpa, times(1)).getByCpf(cpf);
        assertTrue(clienteAtual.isPresent());
        assertEquals(clienteAtual.get().getId(), clienteExpected.getId());
        assertNull(clienteAtual.get().getCpf());
        assertNull(clienteAtual.get().getEmail());
        assertEquals(clienteAtual.get().getNome(), clienteExpected.getNome());
    }

    @Test
    void deveRetornarEmpty_quandoCpfNaoEstiverCadastrado() {
        // given
        String cpf = "12345678958";

        when(clienteRepositoryJpa.getByCpf(cpf)).thenReturn(Optional.empty());

        // when
        Optional<Cliente> clienteAtual = underTest.buscarClientePorCpf(cpf);

        // then
        verify(clienteRepositoryJpa, times(1)).getByCpf(cpf);
        assertFalse(clienteAtual.isPresent());
    }

    @Test
    void deveCriarCliente_quandoTodosOsDadosForemInformados() throws IOException {
        // given
        Cliente clienteRequest = ClienteHelper.gerarClienteRequest();
        ClienteEntity clienteEntity = ClienteHelper.gerarClienteEntity();

        when(clienteRepositoryJpa.save(any(ClienteEntity.class))).thenReturn(clienteEntity);

        // when
        Cliente clienteAtual = underTest.criarCliente(clienteRequest);

        // then
        verify(clienteRepositoryJpa, times(1)).save(any(ClienteEntity.class));
        assertNotNull(clienteAtual);
        assertEquals(clienteAtual.getId(), clienteEntity.getId());
        assertNull(clienteAtual.getCpf());
        assertNull(clienteAtual.getEmail());
        assertEquals(clienteAtual.getNome(), clienteEntity.getNome());
    }

}
