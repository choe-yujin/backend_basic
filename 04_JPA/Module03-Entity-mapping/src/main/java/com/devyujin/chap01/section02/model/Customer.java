package com.devyujin.chap01.section02.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//DB에서는 커스터머가 order 참조하고 있지 않다.
// 우리가 객체지향적으로 봤을때
// 우리가 지금 만드는 프로그램은 주문 내리는건 고객으로부터 생성된다.
// 고객으로부터 주문을 내릴 수 있도록 만들어줘야한다. = 고객이 주문을 포함하는 관계를 만들어줘야 한다.
@Entity(name = "section02-customers")
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "name")
    private String name;

    // 왜 필요하냐? 이 필드를 어디서 관리되고 있는지 찾아야한다. mappedBy로.
    // cascade? 고객 생성, 삭제될때 고객과 연관관계 맺고있는 주문건들을 어떻게 관리할거야? 영속성 전이.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // 1:N 관계를 명시될때. 보통 1이되는쪽의 테이블의 컬럼상에는 명시하지는 않긴한다.
    private List<Order> orders = new ArrayList<>();

    protected Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                " 고객 번호 =" + customerId +
                ", 고객 이름 ='" + name + '\'' +
                '}';
    }
}
