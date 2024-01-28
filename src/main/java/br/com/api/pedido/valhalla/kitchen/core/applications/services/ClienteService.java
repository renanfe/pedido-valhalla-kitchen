package br.com.api.pedido.valhalla.kitchen.core.applications.services;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ClienteForm;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.ClienteMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.ClienteRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    public ClienteService(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> buscaClienteCpf(final String cpf){
        return clienteRepository.buscarClientePorCpf(cpf);
    }

    public Cliente criarCliente(final ClienteForm clienteForm){
        return clienteRepository.criarCliente(ClienteMapper.clienteFormToCliente(clienteForm));
    }

}
