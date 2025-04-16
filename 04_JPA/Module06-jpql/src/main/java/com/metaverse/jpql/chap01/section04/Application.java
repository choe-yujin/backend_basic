package com.metaverse.jpql.chap01.section04;

import com.metaverse.jpql.chap01.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();
        String badJpql = "SELECT c FROM Course c where c.price >= 300";
        TypedQuery<Course> coursesType = em.createQuery(badJpql, Course.class);
        coursesType.getResultList().forEach(course -> System.out.println(course.getTitle() + "-" + course.getPrice()));

        // 파라미터 바인딩
        String jpql = "SELECT c FROM Course c WHERE c.title LIKE :title";
        TypedQuery<Course> coursesType2 = em.createQuery(jpql, Course.class);
        coursesType2.setParameter("title", "%데이터%");

        coursesType2.getResultList().forEach(course -> System.out.println(course.getTitle()));
    }
}
