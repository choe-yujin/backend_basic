package com.devyujin.jdbc.service;

import com.devyujin.jdbc.dao.LessonDao;
import com.devyujin.jdbc.model.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 📌 LessonService (비즈니스 로직 처리)
 * - Lesson 데이터를 관리하는 서비스 레이어
 * - LessonDao를 활용하여 데이터베이스와 상호작용
 * - 트랜잭션 관리 및 예외 처리 포함
 */
public class LessonService {


    private static final Logger log = LoggerFactory.getLogger(LessonService.class);
    private final LessonDao lessonDao;
    private final Connection connection;

    /**
     * 📌 생성자 주입 (의존성 주입)
     *
     * @param connection 데이터베이스 연결 객체
     */
    public LessonService(Connection connection) {
        this.connection = connection;
        this.lessonDao = new LessonDao(connection);
    }

    /**
     * 📌 전체 Lesson 목록 조회
     * - 데이터베이스에서 모든 Lesson 정보를 가져옴
     *
     * @return List<Lesson> - 강의 목록
     * @throws SQLException 데이터베이스 접근 오류 발생 시
     */
    public List<Lesson> getAllLessons() throws SQLException {
        List<Lesson> lessons = lessonDao.getAllLesson();

        if (lessons.isEmpty()) {
            log.warn("조회된 강의가 없습니다.");
        }
        return lessons;
    }

    /**
     * 📌 특정 제목을 가진 Lesson 조회
     * - 주어진 제목을 기준으로 강의 조회
     *
     * @param title 강의 제목
     * @return Lesson - 조회된 Lesson 객체 (없으면 예외 발생)
     * @throws IllegalArgumentException 해당 제목의 강의가 없을 경우 발생
     * @throws SQLException             데이터베이스 접근 중 오류 발생 시
     */
    public Lesson getLessonByTitle(String title) throws SQLException {
        Lesson lesson = lessonDao.getLesson(title);
        if (lesson == null) {
            throw new IllegalArgumentException("해당 제목의 강의를 찾을 수 없습니다.");
        }
        return lesson;
    }
}
