package com.daltonvlm.cursomc.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClientOrderItemPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private ClientOrder order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ClientOrder getOrders() {
        return order;
    }

    public void setOrder(ClientOrder clientOrder) {
        this.order = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrderItemPK that = (ClientOrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
