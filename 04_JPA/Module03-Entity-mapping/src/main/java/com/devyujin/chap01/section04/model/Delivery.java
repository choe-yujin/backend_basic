package com.devyujin.chap01.section04.model;

import jakarta.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private int deliveryId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    public Delivery() {}

    public Delivery(String status, String address) {
        this.status = status;
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", order=" + order +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
