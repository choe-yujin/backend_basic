package com.metaverse.academy;

import com.metaverse.academy.common.util.ConsoleHelper;
import com.metaverse.academy.view.LoginView;
import com.metaverse.academy.view.ProfileView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // 스캐너 생성
        Scanner scanner = new Scanner(System.in);
        ConsoleHelper consoleHelper = new ConsoleHelper(scanner);
        
        // 로그인 뷰 생성
        LoginView loginView = new LoginView(consoleHelper);
        
        // 로그인 시도
        boolean loggedIn = loginView.showLoginForm();
        
        // 로그인 성공 시 프로필 수정 화면으로 이동
        if (loggedIn) {
            // 임시 사용자 ID와 역할 (실제로는 로그인 결과에서 얻어야 함)
            Long userId = 1L;
            String userRole = "STUDENT"; // 또는 "ADMIN", "INSTRUCTOR"
            
            // 프로필 뷰 생성 및 표시
            ProfileView profileView = new ProfileView(consoleHelper, userId, userRole);
            profileView.showProfile();
        }
        
        // 스캐너 닫기
        scanner.close();
        
        System.out.println("프로그램을 종료합니다.");
    }
}
