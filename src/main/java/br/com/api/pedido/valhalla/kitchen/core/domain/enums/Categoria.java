package br.com.api.pedido.valhalla.kitchen.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Categoria {
    LANCHE(1, "Lanche"),
    ACOMPANHAMENTO(2, "Acompanhamento"),
    BEBIDA(3, "Bebida");

    private final Integer id;
    private final String descricao;

    public static Categoria getById(final Integer id) {
        return Arrays.stream(Categoria.values())
                .filter(status -> status.getId().
                        equals(id))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
    }

    public static Categoria getByDescricao(final String descricao) {
        return Arrays.stream(Categoria.values())
                .filter(status -> status.getDescricao().equals(descricao))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
    }
}
