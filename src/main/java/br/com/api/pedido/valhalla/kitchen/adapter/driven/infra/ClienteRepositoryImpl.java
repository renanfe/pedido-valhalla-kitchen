package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ClienteEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa.ClienteRepositoryJpa;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.ClienteMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.ClienteRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    private final ClienteRepositoryJpa clienteRepositoryJpa;

    public ClienteRepositoryImpl(final ClienteRepositoryJpa clienteRepositoryJpa) {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
    }

    @Override
    public Optional<Cliente> buscarClientePorCpf(final String cpf) {
        return clienteRepositoryJpa.getByCpf(cpf).map(ClienteMapper::clienteEntityToCliente);
    }

    @Override
    public Cliente criarCliente(final Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepositoryJpa.save(ClienteMapper.clienteToEntity(cliente));
        return ClienteMapper.clienteEntityToCliente(clienteEntity);
    }
}
