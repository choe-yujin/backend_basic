package com.metaverse.jpql.chap01.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        // Select는 트랜잭션에 해당하지 않기에 tx 안써줘도 됨.
        String sql = "SELECT * FROM courses where price >= 300";
        List<Object[]> result = em.createNativeQuery(sql).getResultList();
        System.out.println(result.size());

        System.out.println();
        // 왜 문제?
        // 1. select할 때 courses 테이블에 의존되어있다.
        // 2. 타입 안정성. List의 타입이 Object타입이다.(모든걸 다 받을 수 있음)
        //    Object타입이라서 데이터가 course라는것을 보장할 수 없다.
        // 3. 수동 바인딩하는 문제.
        // 4. 한번 조회 후 영속성 컨텍스트에 저장되어야하는데, result2 조회하려고 보니까 쿼리를 또 조회하고 있다.
        //    영속성 컨텍스트에 저장되어지지 않고 있다.

        for (Object[] row : result) {
            System.out.println("Course Id : " + row[0] + ", title : " + row[1]);
        }

        List<Object[]> result2 = em.createNativeQuery(sql).getResultList();
        System.out.println(result2.size());

        em.close();
        emf.close();
    }
}
