package com.daltonvlm.cursomc.domain.enums;

public enum Profile {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private final int code;
    private final String description;

    Profile(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Profile ps : Profile.values()) {
            if (code.equals(ps.getCode())) {
                return ps;
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
