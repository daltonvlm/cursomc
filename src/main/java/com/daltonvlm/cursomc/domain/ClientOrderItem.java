package com.daltonvlm.cursomc.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ClientOrderItem implements Serializable {
    @EmbeddedId
    private ClientOrderItemPK id = new ClientOrderItemPK();
    private Double discount;
    private Integer quantity;
    private Double price;

    public ClientOrderItem() {
    }

    public ClientOrderItem(ClientOrder clientOrder, Product product, Double discount, Integer quantity, Double price) {
        this.id.setClientOrder(clientOrder);
        this.id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public ClientOrder getClientOrder() {
        return id.getClientOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public ClientOrderItemPK getId() {
        return id;
    }

    public void setId(ClientOrderItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrderItem that = (ClientOrderItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
