package com.devyujin.chap01.section04.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Delivery delivery;

    @Column(name = "order_date")
    private LocalDate orderDate = LocalDate.now();

    public Order() {}

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }



    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    @Override
    public String toString() {
        return "Order{" +
                "주문 번호=" + orderId +
                ", 주문일=" + orderDate +
                '}';
    }
}
