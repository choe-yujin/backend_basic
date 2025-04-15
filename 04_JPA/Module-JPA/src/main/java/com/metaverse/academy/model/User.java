package com.metaverse.academy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {  // abstract로 변경

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "user_seq_generator")
    @TableGenerator(
            name = "user_seq_generator",
            table = "hibernate_sequences",
            pkColumnName = "sequence_name",
            valueColumnName = "next_val",
            pkColumnValue = "user_seq"
    )
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // 기본 생성자
    public User() {
        this.createdAt = LocalDateTime.now();  // 현재 시간으로 자동 설정
    }

    // 매개변수 있는 생성자
    public User(String username, String email, String passwordHash) {
        this();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Getter 및 Setter 메서드
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 추상 메서드로 변경 (구현을 강제)
    /**
     * 사용자 로그인 기능 
     * 
     * @param password 로그인할 비밀번호
     * @return 로그인 성공 여부
     */
    public abstract boolean login(String password);
    
    /**
     * 사용자 프로필 업데이트 기능
     * 
     * @param username 새 사용자 이름
     * @param email 새 이메일
     * @return 업데이트 성공 여부
     */
    public abstract boolean updateProfile(String username, String email);
}
