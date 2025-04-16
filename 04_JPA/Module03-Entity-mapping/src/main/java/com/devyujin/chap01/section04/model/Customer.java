package com.devyujin.chap01.section04.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id // entity의 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk값 중복되지않게 하는 전략 중 하나 지정
    @Column(name = "customer_id") // java네이밍과 엔티티 네이밍이 달라서
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true) // 영속성컨텍스트에서 지우는 것
    private List<Order> orders = new ArrayList<>();

    protected Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
