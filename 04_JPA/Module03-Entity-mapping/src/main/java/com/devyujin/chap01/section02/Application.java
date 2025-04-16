package com.devyujin.chap01.section02;

import com.devyujin.chap01.section02.model.Customer;
import com.devyujin.chap01.section02.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
* 양방향 연관관계 매핑(1:n <-> N:1) n:n관계를 의미하는 것이 아님 주의
* customer쪽에 order를 추가하게 되면 customer도 order참조, order도 customer를 참조하게 된다.
* 하나의 주문은 고객 하나인데 주문건을 여러개 생성할 수 있다.
* - 고객은 여러 주문을 가질 수 있고, 각 주문은 하나의 고객에게 귀속된다.
* - 이 경우, Order 클래스에서 Customer를 참조하고
*   Customer 클래스에서 Order 목록을 참조하여 양방향 관계를 형성한다.
* */
public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // 트랜잭션을 왜 거는가? 데이터일관성 보장하기 위해.
        tx.begin();

        try {
            Customer customer = new Customer("홍길동");
            Order order = new Order(customer);

            customer.addOrder(order);
            em.persist(customer);

            em.clear();

            Customer foundCustomer = em.find(Customer.class, customer.getCustomerId());

            System.out.println("주문 목록: ");
            foundCustomer.getOrders().forEach(System.out::println);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }


    }
}
