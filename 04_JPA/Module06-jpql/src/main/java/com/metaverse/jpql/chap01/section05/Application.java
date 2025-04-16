package com.metaverse.jpql.chap01.section05;

import com.metaverse.jpql.chap01.section05.dto.CourseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT NEW com.metaverse.jpql.chap01.section05.dto.CourseDTO(c.courseId, c.title, COUNT(1))"+
                " FROM Course c JOIN c.lessons l GROUP BY c.courseId, c.title" +
                " HAVING COUNT(1) > :cnt";

        TypedQuery<CourseDTO> query = em.createQuery(jpql, CourseDTO.class);
        query.setParameter("cnt", 1);
        List<CourseDTO> result = query.getResultList();
        result.forEach(System.out::println);
    }
}
