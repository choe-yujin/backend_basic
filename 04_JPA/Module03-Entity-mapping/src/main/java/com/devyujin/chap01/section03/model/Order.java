package com.devyujin.chap01.section03.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "section03-order")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "customer_id")
    private int customerId;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Column(name = "order_date")
    private LocalDate date = LocalDate.now();

    protected Order() {}

    public Order(int customerId) {
        this.customerId = customerId;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", delivery=" + delivery +
                ", date=" + date +
                '}';
    }
}
