package com.daltonvlm.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ClientOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;
    @JsonManagedReference
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
}
