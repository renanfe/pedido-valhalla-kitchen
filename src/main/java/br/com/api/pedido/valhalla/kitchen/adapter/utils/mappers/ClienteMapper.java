package br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ClienteEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ClienteForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;

public class ClienteMapper {

    private ClienteMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static ClienteEntity clienteToEntity(final Cliente cliente) {
        return ClienteEntity.builder()
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .nome(cliente.getNome()).build();
    }

    public static Cliente clienteEntityToCliente(final ClienteEntity clienteEntity) {
        return Cliente.builder()
                .id(clienteEntity.getId())
                .email(clienteEntity.getEmail())
                .cpf(clienteEntity.getCpf())
                .nome(clienteEntity.getNome()).build();
    }

    public static Cliente clienteFormToCliente(final ClienteForm clienteForm) {
        return Cliente.builder()
                .cpf(clienteForm.getCpf())
                .email(clienteForm.getEmail())
                .nome(clienteForm.getNome()).build();
    }
}
