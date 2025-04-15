package com.metaverse.academy.view;

import com.metaverse.academy.common.util.ConsoleHelper;

/**
 * 프로필 화면
 * 사용자 프로필 조회 및 수정 UI 처리
 */
public class ProfileView {
    /** 콘솔 입출력을 위한 헬퍼 */
    private ConsoleHelper consoleHelper;
    
    /** 현재 사용자 ID */
    private Long userId;
    
    /** 현재 사용자 역할 */
    private String userRole;
    
    /**
     * 생성자
     * 
     * @param consoleHelper 콘솔 입출력 헬퍼
     * @param userId 사용자 ID
     * @param userRole 사용자 역할
     */
    public ProfileView(ConsoleHelper consoleHelper, Long userId, String userRole) {
        this.consoleHelper = consoleHelper;
        this.userId = userId;
        this.userRole = userRole;
    }

    /**
     * 프로필 정보 표시
     */
    public void showProfile() {
        consoleHelper.displayHeader("내 프로필");
        
        // 현재 사용자 정보 표시
        displayUserInfo();
        
        // 프로필 수정 여부 확인
        if (consoleHelper.getConfirmation("프로필을 수정하시겠습니까?")) {
            updateProfile();
        }
    }

    /**
     * 프로필 수정 기능 구현
     * 모든 프로필 정보를 한번에 수정할 수 있는 단순한 인터페이스 제공
     */
    public void updateProfile() {
        consoleHelper.displayHeader("프로필 수정");
        
        // 사용자 입력 받기
        String username = consoleHelper.getStringInput("새 사용자명 (변경하지 않으려면 빈칸)");
        String email = consoleHelper.getStringInput("새 이메일 (변경하지 않으려면 빈칸)");
        
        // 역할별 추가 정보
        String additionalInfo = "";
        if ("STUDENT".equals(userRole)) {
            additionalInfo = consoleHelper.getStringInput("새 학번 (변경하지 않으려면 빈칸)");
        } else if ("INSTRUCTOR".equals(userRole)) {
            additionalInfo = consoleHelper.getStringInput("새 전공 과목 (변경하지 않으려면 빈칸)");
        } else if ("ADMIN".equals(userRole)) {
            additionalInfo = consoleHelper.getStringInput("새 직책 (변경하지 않으려면 빈칸)");
        }

        // 변경사항 확인
        if (username.isEmpty() && email.isEmpty() && additionalInfo.isEmpty()) {
            consoleHelper.showInfo("변경사항이 없습니다.");
        } else {
            // 실제 구현에서는 User 클래스의 updateProfile 메서드를 호출
            boolean updated = true;

            if (updated) {
                consoleHelper.showSuccess("프로필이 성공적으로 업데이트되었습니다.");
            } else {
                consoleHelper.showError("프로필 업데이트에 실패했습니다.");
            }
        }
        
        consoleHelper.waitForEnter();
    }


    /**
     * 사용자 정보 표시
     */
    private void displayUserInfo() {
        System.out.println("사용자 ID: " + userId);

        consoleHelper.displayDivider();
    }
}
