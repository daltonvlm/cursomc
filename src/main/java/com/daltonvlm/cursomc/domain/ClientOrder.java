package com.daltonvlm.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class ClientOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "id.order")
    private Set<ClientOrderItem> orderItems = new HashSet<>();

    public ClientOrder() {
    }

    public ClientOrder(Integer id, Date date, Client client, Address address) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.address = address;
    }

    public double getTotalValue() {
        double total = 0;

        for (ClientOrderItem coi : orderItems) {
            total += coi.getSubTotal();
        }

        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address deliveryAddress) {
        this.address = deliveryAddress;
    }

    public Set<ClientOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<ClientOrderItem> items) {
        this.orderItems = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrder clientOrder = (ClientOrder) o;
        return Objects.equals(id, clientOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        final StringBuilder sb = new StringBuilder();
        sb
                .append("Order number: ").append(id)
                .append(", Date: ").append(sdf.format(date))
                .append(", Client: ").append(client.getName())
                .append(", Payment status: ").append(payment.getPaymentState().getDescription())
                .append("\nDetails:\n");

        for (ClientOrderItem orderItem : orderItems) {
            sb.append(orderItem);
        }

        sb.append("Total value: ").append(nf.format(getTotalValue()));
        return sb.toString();
    }
}
