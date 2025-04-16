package com.devyujin.chap01.section03.model;

import jakarta.persistence.*;

@Entity(name = "section03-Delivery")
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "delivery_id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    /*
    * 연관관
    * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "status", nullable = false)
    private String status;

    protected Delivery() {}

    public Delivery(String address, Order order) {
        this.address = address;
        this.order = order;
        this.status = "new";
    }
}
