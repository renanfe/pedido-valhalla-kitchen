package br.com.api.pedido.valhalla.kitchen.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Cliente {
    private UUID id;
    private String cpf;
    private String email;
    private String nome;

}
