package com.devyujin.jdbc.service;

import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.model.Lesson;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LessonServiceTest {

    private static final Logger log = LoggerFactory.getLogger(LessonServiceTest.class);
    private Connection connection;
    private LessonService lessonService;

    private static final String TEST_TITLE = "Junit 테스트 강의";
    private static final int TEST_COURSE_ID = 1;
    private static final String TEST_CONTENT = "Junit을 활용한 강의 테스트";
    private static final String TEST_VIDEO_URL = "http://test.url";

    /**
     * 📌 테스트 시작 전 환경 설정
     * - 데이터베이스 연결 및 트랜잭션 관리 (Rollback)
     */
    @BeforeEach
    void setUp() {
        try {
            connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false); // 자동 커밋 방지
            lessonService = new LessonService(connection);

            // 📌 Arrange: 테스트 데이터 삽입 (PreparedStatement 활용)
            String insertSql = "INSERT INTO lessons (course_id, title, content, video_url, created_at) VALUES (?, ?, ?, ?, now())";

            try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                pstmt.setInt(1, TEST_COURSE_ID);
                pstmt.setString(2, TEST_TITLE);
                pstmt.setString(3, TEST_CONTENT);
                pstmt.setString(4, TEST_VIDEO_URL);
                pstmt.executeUpdate();
                log.info("✅ 테스트용 강의 추가 완료");
            }
        } catch (SQLException e) {
            log.error("🚨 데이터 삽입 중 오류 발생: {}", e.getMessage());
        } catch (Exception e) {
            log.error("🚨 알 수 없는 오류 발생: {}", e.getMessage());
        }
    }

    /**
     * 📌 전체 강의 조회 테스트
     */
    @Test
    @DisplayName("전체 Lesson 조회 테스트")
    void testGetAllLessons() throws SQLException {
        // Act: 서비스 메서드 실행
        List<Lesson> lessons = lessonService.getAllLessons();

        // Assert: 결과 검증
        assertFalse(lessons.isEmpty(), "강의 목록이 비어 있지 않아야 합니다.");
        log.info("강의 목록 조회 테스트 성공!");
    }

    /**
     * 📌 특정 제목을 가진 강의 조회 테스트
     */
    @Test
    @DisplayName("단일 Lesson 조회 테스트")
    void testGetLessonByTitle() throws SQLException {
        // Act: 서비스 메서드 실행
        Lesson lesson = lessonService.getLessonByTitle(TEST_TITLE);

        // Assert: 결과 검증
        assertNotNull(lesson, "강의가 존재해야 합니다.");
        assertEquals(TEST_TITLE, lesson.getTitle(), "강의 제목이 일치해야 합니다.");
        log.info("단일 강의 조회 테스트 성공!");
    }

    /**
     * 📌 존재하지 않는 강의 조회 테스트
     * - IllegalArgumentException 발생 예상
     */
    @Test
    @DisplayName("없는 강의 조회 테스트 (예외 발생)")
    void testGetLessonByTitle_NotFound() {
        // Assert & Act
        assertThrows(IllegalArgumentException.class, () -> {
            lessonService.getLessonByTitle("존재하지 않는 강의");
        }, "존재하지 않는 강의를 조회하면 예외가 발생해야 합니다.");
        log.info("없는 강의 조회 예외 테스트 성공!");
    }

    /**
     * 📌 테스트 종료 후 데이터 롤백
     */
    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback(); // 롤백하여 테스트 데이터 삭제
        connection.setAutoCommit(true);
        connection.close();
        log.info("테스트 데이터 롤백 완료");
    }
}