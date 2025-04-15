package com.devyujin.jdbc.view;

import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.model.Lesson;
import com.devyujin.jdbc.service.LessonService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 📌 LessonView (사용자 인터페이스)
 * - 사용자가 Lesson 정보를 조회할 수 있도록 인터페이스 제공
 * - 콘솔을 통해 입력을 받아 LessonService와 상호작용
 */
public class LessonView {
    private final LessonService lessonService;
    private final Scanner scanner;

    /**
     * 📌 생성자 (Connection 주입)
     * - 데이터베이스 연결 객체를 받아 LessonService 초기화
     */
    public LessonView(Connection connection) {
        this.lessonService = new LessonService(connection);
        this.scanner = new Scanner(System.in);
    }





    /**
     * 📌 메인 메뉴 표시 및 사용자 입력 처리
     */
    public void showMenu() {
        while (true) {
            System.out.println("===== 강의 관리 시스템 =====");
            System.out.println("1. 전체 강의 조회");
            System.out.println("2. 강의 제목으로 조회");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> displayAllLessons();
                case 2 -> searchLessonByTitle();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    /**
     * 📌 모든 강의 목록 출력
     * - LessonService를 통해 강의 목록을 가져와 출력
     */
    private void displayAllLessons() {
        try {
            List<Lesson> lessons = lessonService.getAllLessons();

            if (lessons.isEmpty()) {
                System.out.println("📌 조회된 강의가 없습니다.");
            } else {
                System.out.println("\n📌 강의 목록:");
                for (Lesson lesson : lessons) {
                    System.out.println(lesson);
                }
            }
        } catch (SQLException e) {
            System.out.println("🚨 강의 목록 조회 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 📌 특정 제목을 가진 강의 검색
     * - 사용자가 제목을 입력하면 해당 강의 정보를 출력
     */
    private void searchLessonByTitle() {
        System.out.print("📌 검색할 강의 제목을 입력하세요: ");
        String title = scanner.nextLine();

        try {
            Lesson lesson = lessonService.getLessonByTitle(title);
            System.out.println("\n📌 검색된 강의 정보:");
            System.out.println(lesson);
        } catch (IllegalArgumentException e) {
            System.out.println("🚨 오류: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("🚨 데이터베이스 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 📌 실행 메서드 (main)
     * - JDBCConnection을 생성하고 LessonView 실행
     */
    public static void main(String[] args) {
        try (Connection connection = JDBCConnection.getConnection()) {
            LessonView lessonView = new LessonView(connection);
            lessonView.showMenu();
        } catch (SQLException e) {
            System.out.println("🚨 데이터베이스 연결 실패: " + e.getMessage());
        }
    }
}