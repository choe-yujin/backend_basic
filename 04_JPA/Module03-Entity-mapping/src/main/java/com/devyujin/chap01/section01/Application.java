package com.devyujin.chap01.section01;

import com.devyujin.chap01.section01.model.Customer;
import com.devyujin.chap01.section01.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //트랜잭션이 시작됨을 의미함
        tx.begin();

        try {
            Customer customer = new Customer("홍길동");
            em.persist(customer);

            Order order = new Order(customer);
            em.persist(order);

            tx.commit();

            em.clear();
            Order foundOrder = em.find(Order.class, order.getOrderId());
            System.out.println("==========Customer 조회하기 이전==========");
            System.out.println(foundOrder);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
