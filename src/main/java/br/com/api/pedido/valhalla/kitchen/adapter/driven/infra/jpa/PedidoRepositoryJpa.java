package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepositoryJpa extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByStatusIn(List<String> status);

}
