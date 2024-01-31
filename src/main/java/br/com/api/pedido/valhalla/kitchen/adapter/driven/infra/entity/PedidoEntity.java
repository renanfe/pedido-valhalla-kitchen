package br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@SequenceGenerator(name = "pedido_generator", sequenceName = "pedido_id_seq", allocationSize = 1)
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "clienteId")
    private UUID clienteId;

    @Column(name = "status")
    @Builder.Default
    private String status = "Recebido";

    @Column(name = "status_pagamento")
    @Builder.Default
    private String statusPagamento = "Aguardando";
}
