package com.devyujin.jdbc;

import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.view.LessonView;
import com.devyujin.jdbc.view.RoleView;
import com.devyujin.jdbc.view.UserView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LMS 관리 시스템 =====");
            System.out.println("1. 역할(Role) 관리");
            System.out.println("2. 강의(Lesson) 관리");
            System.out.println("3. 사용자(User) 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> startRoleManagement(connection);
                case 2 -> startLessonManagement(connection);
                case 3 -> startUserManagement(connection);
                case 0 -> {
                    connection.close();
                    System.out.println("🚀 프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("❌ 잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }


    /**
     * 📌 역할(Role) 관리 시작
     * - 역할(Role) 관련 기능 실행
     */
    private static void startRoleManagement(Connection connection) {
        RoleView roleView = new RoleView(connection);
        roleView.showMenu();
    }

    /**
     * 📌 강의(Lesson) 관리 시작
     * - 강의(Lesson) 관련 기능 실행
     */
    private static void startLessonManagement(Connection connection) {
        LessonView lessonView = new LessonView(connection);
        lessonView.showMenu();
    }

    /**
     * 📌 사용자(User) 관리 시작
     * - 사용자(User) 관련 기능 실행
     */
    private static void startUserManagement(Connection connection) {
        UserView userView = new UserView(connection);
        userView.showMenu();
    }

}