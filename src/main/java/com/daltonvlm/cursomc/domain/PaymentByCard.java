package com.daltonvlm.cursomc.domain;

import com.daltonvlm.cursomc.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("paymentByCard")
public class PaymentByCard extends Payment {
    private Integer installments;

    public PaymentByCard() {
    }

    public PaymentByCard(Integer id, PaymentState state, ClientOrder order, Integer installments) {
        super(id, state, order);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
