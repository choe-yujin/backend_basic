package com.metaverse.academy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {
    @Column(nullable = false, unique = true)
    private String studentId;

    public Student() {}

    public Student(String username, String email, String passwordHash, String studentId) {
        super(username, email, passwordHash);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean login(String password) {
        // 실제 구현에서는 비밀번호 해시를 비교해야 함
        // 여기서는 간단하게 모의 구현
        if (getEmail().equals("student@example.com") && password.equals("student123")) {
            System.out.println("학생 로그인 성공");
            return true;
        }
        System.out.println("로그인 실패");
        return false;
    }

    @Override
    public boolean updateProfile(String username, String email) {
        // 기본 프로필 정보 업데이트
        boolean updated = false;
        
        if (username != null && !username.isEmpty()) {
            setUsername(username);
            updated = true;
        }
        
        if (email != null && !email.isEmpty()) {
            setEmail(email);
            updated = true;
        }
        
        if (updated) {
            System.out.println("학생 프로필 업데이트 성공");
        }
        
        return updated;
    }
    
    /**
     * 학생 전용 프로필 업데이트 메서드
     * 학번 정보 추가 업데이트
     */
    public boolean updateProfile(String username, String email, String studentId) {
        // 기본 정보 업데이트
        boolean updated = updateProfile(username, email);
        
        // 학번 업데이트
        if (studentId != null && !studentId.isEmpty()) {
            this.studentId = studentId;
            updated = true;
            System.out.println("학번 업데이트 성공: " + studentId);
        }
        
        return updated;
    }
}
