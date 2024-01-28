package br.com.api.pedido.valhalla.kitchen.core.applications.ports;

import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> buscarClientePorCpf(final String cpf);
    Cliente criarCliente(final Cliente cliente);
}
