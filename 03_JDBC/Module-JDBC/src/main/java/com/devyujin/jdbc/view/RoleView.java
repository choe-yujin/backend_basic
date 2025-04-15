package com.devyujin.jdbc.view;

import com.devyujin.jdbc.model.Role;
import com.devyujin.jdbc.service.RoleService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 📌 RoleView (View 계층)
 * - 사용자 입력을 받아 Role 정보를 조회하는 콘솔 기반 UI
 * - RoleService를 호출하여 역할 데이터를 가져옴
 */
public class RoleView {

    private final RoleService roleService;
    private final Scanner scanner;

    /**
     * 📌 생성자 (Connection 주입)
     * - 데이터베이스 연결을 `Application`에서 주입받아 사용
     *
     * @param connection 데이터베이스 연결 객체
     */
    public RoleView(Connection connection) {
        this.roleService = new RoleService(connection);
        this.scanner = new Scanner(System.in);
    }

    /**
     * 📌 역할 조회 메뉴 시작
     * - 사용자 입력을 받아 역할 정보를 조회
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n===== 역할 관리 시스템 =====");
            System.out.println("1. 역할 조회 (ID로 검색)");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1 -> searchRoleById();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    /**
     * 📌 역할 조회 (ID로 검색)
     * - 사용자로부터 ID를 입력받아 역할을 조회
     */
    private void searchRoleById() {
        System.out.print("조회할 역할 ID를 입력하세요: ");
        int roleId = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        try {
            Role role = roleService.getRoleById(roleId);
            System.out.println("✅ 조회된 역할 정보: " + role);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ 오류: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ 데이터베이스 오류 발생: " + e.getMessage());
        }
    }
}