package com.devyujin.chap01.section02.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "section02-order")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER랑 같이 비교 실습
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date")
    private LocalDate orderDate = LocalDate.now();

    protected Order() {
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                '}';
    }
}