package com.daltonvlm.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class ClientOrderItem implements Serializable {
    @JsonIgnore
    @EmbeddedId
    private ClientOrderItemPK id = new ClientOrderItemPK();
    private Double discount;
    private Integer quantity;
    private Double price;

    public ClientOrderItem() {
    }

    public ClientOrderItem(ClientOrder clientOrder, Product product, Double discount, Integer quantity, Double price) {
        this.id.setOrder(clientOrder);
        this.id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal() {
        return (price - discount) * quantity;
    }

    @JsonIgnore
    public ClientOrder getOrder() {
        return id.getOrder();
    }

    public void setOrder(ClientOrder order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        final StringBuilder sb = new StringBuilder();
        sb
                .append(getProduct().getName())
                .append(", Quantity: ").append(quantity)
                .append(", Unit price: ").append(nf.format(price))
                .append(", Subtotal: ").append(nf.format(getSubTotal()))
                .append("\n");

        return sb.toString();
    }
}
