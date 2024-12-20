package com.tis2.AppRh.entities.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

public enum CategoriaVaga {
    FINANCEIRO(1),
    DESENVOLVIMENTO(2),
    ADMINISTRATIVO(3);

    private int code;

    private CategoriaVaga (int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CategoriaVaga valueOf(int code) {
        for (CategoriaVaga value: CategoriaVaga.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalIdentifierException("Invalid OrderStatus code");
    }
}
