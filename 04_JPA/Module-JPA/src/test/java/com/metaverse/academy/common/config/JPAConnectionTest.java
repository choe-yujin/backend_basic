package com.metaverse.academy.common.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * JPA EntityManager 및 데이터베이스 연결 테스트 클래스
 *
 * 이 클래스는 JPA를 통한 데이터베이스 연결을 테스트합니다.
 */
class JPAConnectionTest {
    
    private static EntityManagerFactory emf;
    
    @BeforeAll
    static void setUp() {
        System.out.println("Setting up JPA Connection");
        
        // DB 설정 로드 - 실제 애플리케이션에서 사용하는 설정 활용
        Map<String, String> properties = DatabaseConfig.getJpaProperties();
        
        // EntityManagerFactory 생성 - src/main/resources의 persistence.xml 사용
        emf = Persistence.createEntityManagerFactory("academy-dev", properties);
    }
    
    @Test
    void testConnection() {
        // EntityManager 생성
        EntityManager em = null;
        
        try {
            em = emf.createEntityManager();
            
            // 연결 상태 확인
            Assertions.assertNotNull(em, "1. EntityManager가 생성되지 않았습니다");
            Assertions.assertTrue(em.isOpen(), "2. EntityManager가 성공적으로 열리지 않았습니다");
            
            // 데이터베이스 연결 정보 출력
            System.out.println("=== JPA 연결 테스트 ===");
            DatabaseConfig.printDatabaseInfo();
            System.out.println("JPA EntityManager가 성공적으로 연결되었습니다.");
            
            // 간단한 쿼리 실행
            em.getTransaction().begin();
            Object result = em.createNativeQuery("SELECT 1").getSingleResult();
            em.getTransaction().commit();
            
            System.out.println("데이터베이스 쿼리 실행 결과: " + result);
            
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            Assertions.fail("JPA 연결 테스트 실패: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @AfterAll
    static void tearDown() {
        System.out.println("테스트 종료 및 자원 반납");
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
