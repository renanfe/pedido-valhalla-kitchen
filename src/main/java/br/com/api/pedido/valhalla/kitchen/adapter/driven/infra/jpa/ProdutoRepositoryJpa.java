package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findAllByCategoria(final Integer categoria);
}
