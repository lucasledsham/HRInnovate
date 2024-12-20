package com.tis2.AppRh.entities.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusProcesso {
    EM_ABERTO(1,"Em aberto"),
    FINALIZADO(2,"Finalizado");

    private int code;
    private String description;

    private StatusProcesso(int code, String description) {
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
    public static StatusProcesso valueOf(int code) {
        for (StatusProcesso value : StatusProcesso.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalIdentifierException("Invalid StatusVaga code");
    }

}
