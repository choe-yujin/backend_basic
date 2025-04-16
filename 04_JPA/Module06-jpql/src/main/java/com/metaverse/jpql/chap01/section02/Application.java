package com.metaverse.jpql.chap01.section02;

import com.metaverse.jpql.chap01.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Course c where c.price >= 300";
        List<Course> courses = em.createQuery(jpql, Course.class).getResultList();
        courses.forEach(course -> System.out.println(course.getTitle() + " : " + course.getPrice()));

        String joinJpql = "SELECT c FROM Course c JOIN c.lessons l where c.price >= 300";

        courses = em.createQuery(joinJpql, Course.class).getResultList();
        for (Course course : courses) {
            System.out.println(course.getTitle() + " : " + course.getPrice());
            course.getLessons().forEach(System.out::println);
            System.out.println();
        }
        em.close();
        emf.close();
    }
}
