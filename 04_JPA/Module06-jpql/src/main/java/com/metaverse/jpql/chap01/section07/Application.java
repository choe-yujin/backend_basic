package com.metaverse.jpql.chap01.section07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        // 네이티브 쿼리 작성
        String sql = "SELECT u.username AS student_name, c.title AS course_title, " +
                "CONCAT(i.username, ' (', r.role_name, ')') AS instructor_name " +
                "FROM enrollments e " +
                "JOIN users u ON e.user_id = u.user_id " +
                "JOIN courses c ON e.course_id = c.course_id " +
                "JOIN users i ON c.instructor_id = i.user_id " +
                "JOIN roles r ON i.role_id = r.role_id " +
                "WHERE u.user_id = 6716";
        // Native SQL 사용
        /*
         * section01에서 우리는 native 쿼리를 사용하는 것은 DB에 종속적인 문제가 발생하여 좋지 않다고 배웠다.
         * 그러나 이러한 Native를 사용해야 하는 경우가 존재를 하는데 이러한 경우는 복잡한 쿼리, 혹은 SQL의 고급 내장함수와 같이
         * JPA에서 지원하지 않는 DB의 기능을 사용하는 과정에서는 어쩔 수 없이 사용하게 된다.
         * 또한 DB에 최적화된 쿼리를 작성하기 위해서도 사용할 수 있다.
         * */
        Query query = em.createNativeQuery(sql);

        // 결과 가져오기
        List<Object[]> results = query.getResultList();

        // 결과 출력
        for (Object[] result : results) {
            String studentName = (String) result[0];
            String courseTitle = (String) result[1];
            String instructorName = (String) result[2];
            System.out.println(studentName + " - " + courseTitle + " - " + instructorName);
        }

        em.close();
        emf.close();
    }
}
