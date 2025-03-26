package com.devyujin.jdbc.dao;


import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.model.Lesson;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/*
 * 클래스 단위 인스턴스 유지
 * 테스트 클래스의 인스턴스가 테스트 클래스 내의 모든 테스트 메서드에서 공유된다.
 * 즉, 각 테스트 메서드를 실행할 때마다 새로운 인스턴스가 생성되지 않고, 동일한 인스턴스가 사용되며
 * 이는 상태를 유지할 수 있는 장점을 제공하게 된다.
 * 이를 통해 테스트 간에 공유해야 하는 상태가 있은 경우 유용하게 사용될 수 있다.
 * */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LessonDaoTest {

    private Connection connection;
    private LessonDao lessonDao;
    private static final String TEST_TITLE = "Junit을 활용한 테스트";

    @BeforeEach
    void setUp() {
        try {
            connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false); // 자동으로 커밋을 하지 않도록 설정함.
            lessonDao = new LessonDao(connection); // 🔥 Connection을 주입하여 동일 트랜잭션 사용

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO lessons (course_id, title, content, video_url, created_at) " +
                            "VALUES (1, ?, '테스트를 위한 설명', 'test_url', now())"
            );
            ps.setString(1, TEST_TITLE);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패 : " + e.getMessage());
        }
    }

    @Test
    @DisplayName("전체 데이터 조회 테스트")
    void getAllLesson() {
        // Arrange: 준비 (이미 BeforeEach에서 삽입됨)

        // Act: 테스트 실행
        List<Lesson> lessons = lessonDao.getAllLesson();

        // Assert: 검증
        Assertions.assertFalse(lessons.isEmpty(), "lesson 목록이 비어있으면 안 된다.");
    }


    @Test
    @DisplayName("단일 Lesson 조회")
    void getLessonByTitle() {
        // Act: 테스트 실행
        Lesson lesson = lessonDao.getLesson(TEST_TITLE);

        // Assert: 검증
        Assertions.assertNotNull(lesson, "lesson이 존재해야 한다.");
        Assertions.assertEquals(TEST_TITLE, lesson.getTitle(), "조회한 제목이 일치해야 한다.");
    }


    @AfterAll
    void tearDownDatabase(){
        try {
            connection.rollback();
            connection.setAutoCommit(true); // 자동으로 커밋 할 수 있도록 변경
            connection.close();
            System.out.println("테스트가 무사히 통과하였습니다.");

        }catch (SQLException e){
            System.out.println("테스트가 비정상적으로 종료되었습니다.");
        }

    }
}