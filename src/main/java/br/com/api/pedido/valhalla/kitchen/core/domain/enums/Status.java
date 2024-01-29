package br.com.api.pedido.valhalla.kitchen.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Status {
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    private final Integer id;
    private final String descricao;

    public static Status getById(final Integer id) {
        return Arrays.stream(Status.values())
                .filter(status -> status.getId().
                        equals(id))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    public static Status getByDescricao(final String descricao) {
        return Arrays.stream(Status.values())
                .filter(status -> status.getDescricao().equals(descricao))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }
}
