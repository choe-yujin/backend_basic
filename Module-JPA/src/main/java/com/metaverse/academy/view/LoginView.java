package com.metaverse.academy.view;

import com.metaverse.academy.common.util.ConsoleHelper;

/**
 * 로그인 화면
 * 사용자 로그인 UI 처리
 */
public class LoginView {
    /** 콘솔 입출력을 위한 헬퍼 */
    private ConsoleHelper consoleHelper;
    
    /** 
     * 생성자
     * 
     * @param consoleHelper 콘솔 입출력 헬퍼
     */
    public LoginView(ConsoleHelper consoleHelper) {
        this.consoleHelper = consoleHelper;
    }

    /**
     * 로그인 폼 표시
     *
     * @return 로그인 성공 여부
     */
    public boolean showLoginForm() {
        consoleHelper.clearScreen();
        consoleHelper.displayHeader("로그인");

        String email = consoleHelper.getNonEmptyStringInput("아이디를 입력하세요");
        String password = consoleHelper.getPasswordInput("비밀번호를 입력하세요");

        return true;
    }
}
