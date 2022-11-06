package com.daltonvlm.cursomc.domain.enums;

public enum PaymentState {
    PENDING(1, "Pending"),
    PAID(2, "Paid"),
    CANCELED(3, "Canceled");

    private final int code;
    private final String description;

    PaymentState(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public PaymentState toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (PaymentState ps : PaymentState.values()) {
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
