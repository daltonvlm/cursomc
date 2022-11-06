package com.daltonvlm.cursomc.domain;

import com.daltonvlm.cursomc.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    @Id
    private Integer id;
    private Integer state;
    @OneToOne
    @JoinColumn(name = "client_order_id")
    @MapsId
    private ClientOrder clientOrder;

    public Payment() {
    }

    public Payment(Integer id, PaymentState state, ClientOrder clientOrder) {
        this.id = id;
        this.state = state.getCode();
        this.clientOrder = clientOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getState() {
        return PaymentState.CANCELED.toEnum(state);
    }

    public void setState(PaymentState state) {
        this.state = state.getCode();
    }

    public ClientOrder getOrder() {
        return clientOrder;
    }

    public void setOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
