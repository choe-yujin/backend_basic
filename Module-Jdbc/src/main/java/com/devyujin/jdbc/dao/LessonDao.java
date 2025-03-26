package com.devyujin.jdbc.dao;

/*
 * DAO(Data Access Object)
 * 데이터베이스와의 상호작용을 담당하는 클래스
 * - Lesson 정보를 가져오고 저장하는 기능을 제공
 * - JDBC를 사용하여 SQL 실행
 * - Connection을 외부에서 주입받아 재사용 가능하도록 변경
 */

import com.devyujin.jdbc.model.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * LessonDao (Data Access Object)
 * - Lesson 데이터를 데이터베이스에서 조회, 저장하는 DAO 클래스
 * - JDBC를 사용하여 SQL을 실행
 * - Connection을 외부에서 주입받아 재사용 가능
 */
public class LessonDao {

    private final Connection connection; // 💡 Connection을 멤버 변수로 유지

    /**
     * 생성자 - Connection을 외부에서 주입받아 사용
     * @param connection 데이터베이스 연결 객체 (Connection)
     */
    public LessonDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * 전체 Lesson 목록 조회
     * - `lessons` 테이블에서 모든 데이터를 가져옴
     * - SQL 실행 후 Lesson 객체 리스트로 변환
     *
     * @return List<Lesson> - 모든 강의 목록
     */
    public List<Lesson> getAllLesson() {
        List<Lesson> lessons = new ArrayList<>();

        String sql = "SELECT * FROM lessons";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lessons.add(new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("video_url"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.out.println("getAllLesson() 실행 중 오류 발생");
            e.printStackTrace();
        }

        return lessons;
    }

    /**
     * 📌 특정 제목을 가진 Lesson 조회
     * - 주어진 제목(title)을 기준으로 `lessons` 테이블에서 특정 강의를 조회
     *
     * @param title 조회할 강의의 제목
     * @return Lesson - 조회된 Lesson 객체 (없으면 null 반환)
     */
    public Lesson getLesson(String title) {
        String sql = "SELECT * FROM lessons WHERE title = ?";
        Lesson lesson = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lesson = new Lesson(
                            rs.getInt("lesson_id"),
                            rs.getInt("course_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("video_url"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("getLesson() 실행 중 오류 발생");
            e.printStackTrace();
        }

        return lesson;
    }
}