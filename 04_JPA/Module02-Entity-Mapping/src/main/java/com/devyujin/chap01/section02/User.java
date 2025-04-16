package com.devyujin.chap01.section02;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length= 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    /**
    * 날짜 및 시간 타입 매핑
     * > localDate : 날짜만 저장 (yyyy-mm-dd)
     * > localDateTime : 날짜 + 시간 (yyyy-mm-dd hh:mm:ss) // 날짜는 between으로 범위 연산을 한다.
    * */
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
    * @Enumerated - Enum 타입 필드 매핑
     *
     * 자바의 Enum을 테이블에 저장하는 방법
     * - ORDINAL : enum 순서(int) 저장 -> 위험(DB에 int 타입으로 저장 됨)
     * - STRING : ENUM 이름을 그대로 저장 -> 권장
    * */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_id", nullable = false)
    private Role role;

    public User() {}

    public User(Long id, String username, String email, String passwordHash, LocalDate birthDate, LocalDateTime createdAt, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                ", role=" + role +
                '}';
    }
}
