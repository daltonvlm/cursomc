package com.daltonvlm.cursomc.domain.enums;

public enum ClientType {
    NATURAL_PERSON(1, "Natural Person"),
    LEGAL_PERSON(2, "Legal Person");

    private final int code;
    private final String description;

    ClientType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ClientType toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (ClientType ct : ClientType.values()) {
            if (code.equals(ct.getCode())) {
                return ct;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
