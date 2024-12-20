package com.tis2.AppRh.entities.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusVaga {

    AGUARDANDO_APROVACAO(1, "Aguardando Aprovação"),
    APROVADO(2, "Aprovado"),
    REPROVADO(3, "Reprovado");

    private int code;
    private String description;

    private StatusVaga(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @JsonCreator
    public static StatusVaga valueOf(int code) {
        for (StatusVaga value : StatusVaga.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalIdentifierException("Invalid StatusVaga code");
    }
}
