package com.metaverse.jpql.chap01.section03;

import com.metaverse.jpql.chap01.model.Course;
import com.metaverse.jpql.chap01.model.Lesson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Course c WHERE c.price >= 300";
        List<Course> courses = em.createQuery(jpql, Course.class).getResultList();
        courses.forEach(course -> System.out.println(course.getTitle() + " : " + course.getPrice()));

        String typedJpql = "SELECT c FROM Course c WHERE c.price >= 300";

        // TypedQuery를 사용한 쿼리 실행 (여기서는 List<Course>로 받음)
        TypedQuery<Course> typedQuery = em.createQuery(typedJpql, Course.class); // TypedQuery로 쿼리 생성
        List<Course> courses2 = typedQuery.getResultList(); // getResultList() 호출 시 List<Course>로 반환

        // 결과 출력
        courses2.forEach(course -> System.out.println(course.getTitle() + " : " + course.getPrice()));
    }
}
