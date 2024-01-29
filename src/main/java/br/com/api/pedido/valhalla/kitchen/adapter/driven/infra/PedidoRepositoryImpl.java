package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.PedidoEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.jpa.PedidoRepositoryJpa;
import br.com.api.pedido.valhalla.kitchen.adapter.utils.mappers.PedidoMapper;
import br.com.api.pedido.valhalla.kitchen.core.applications.ports.PedidoRepository;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoRepositoryJpa pedidoRepositoryJpa;

    public PedidoRepositoryImpl(PedidoRepositoryJpa pedidoRepositoryJpa) {
        this.pedidoRepositoryJpa = pedidoRepositoryJpa;
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();

        for (PedidoEntity pedido : pedidoRepositoryJpa.findAll()) {
            pedidos.add(PedidoMapper.pedidoEntityToPedido(pedido));
        }

        return pedidos;
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepositoryJpa.findById(id).map(PedidoMapper::pedidoEntityToPedido);
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoRepositoryJpa.save(PedidoMapper.pedidoToEntity(pedido));
        return PedidoMapper.pedidoEntityToPedido(pedidoEntity);
    }

}