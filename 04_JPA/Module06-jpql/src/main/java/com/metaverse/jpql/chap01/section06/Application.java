package com.metaverse.jpql.chap01.section06;

import com.metaverse.jpql.chap01.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String jpql = "select c from Course c";
        TypedQuery<Course> query = em.createQuery(jpql, Course.class);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List<Course> result = query.getResultList();
        for (Course course : result) {
            System.out.println(course.getCourseId() + " - " + course.getTitle());
        }
    }
}
