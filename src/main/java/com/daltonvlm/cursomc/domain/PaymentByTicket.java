package com.daltonvlm.cursomc.domain;

import com.daltonvlm.cursomc.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentByTicket extends Payment {
    private Date expirationDate;
    private Date paymentDate;

    public PaymentByTicket() {
    }

    public PaymentByTicket(Integer id, PaymentState state, ClientOrder clientOrder, Date expirationDate, Date paymentDate) {
        super(id, state, clientOrder);
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
