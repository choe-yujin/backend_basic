package com.devyujin.chap01.section04;

import com.devyujin.chap01.section04.model.Customer;
import com.devyujin.chap01.section04.model.Delivery;
import com.devyujin.chap01.section04.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.devyujin.demo");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Customer customer = new Customer("심화 고객");
            Order order = new Order(); // heap에 생성됨

            customer.addOrder(order); // order의 주소 전달
            Delivery delivery = new Delivery("READy", "서울");
            order.setDelivery(delivery); // heap에 생성된 order 고객의 주문에 배송이 반영. 얕은 복사

            em.persist(customer);

            tx.commit();

            em.clear();
            System.out.println("n+1문제 실습: 고객목록에서 주문조회");
            List<Customer> customers = em.createQuery("select c from Customer c", Customer.class).getResultList(); // jpql문법. from절은 Entity명

            for (Customer c : customers) {
                System.out.println("고객 이름 : " + c.getName());

                // 해당 시점에서 N+1 문제 발생
                System.out.println("주문 수 : " + c.getOrders().size());
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
