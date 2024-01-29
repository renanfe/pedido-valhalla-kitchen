package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> getByCpf(final String cpf);
}
