-- 데이터베이스 제거 후 생성
DROP DATABASE academy_management;
CREATE DATABASE IF NOT EXISTS academy_management;
USE academy_management;

-- ID 생성을 위한 시퀀스 테이블 (JPA의 GenerationType.TABLE 전략 지원)
CREATE TABLE hibernate_sequences
(
    sequence_name VARCHAR(255) NOT NULL,
    next_val      BIGINT,
    PRIMARY KEY (sequence_name)
);

-- 초기 시퀀스 값 설정 (오직 user_seq만)
INSERT INTO hibernate_sequences(sequence_name, next_val)
VALUES ('user_seq', 1)
    ON DUPLICATE KEY UPDATE next_val = VALUES(next_val);

-- Student 테이블
CREATE TABLE students
(
    id            BIGINT       NOT NULL,
    user_id       VARCHAR(50)  NOT NULL UNIQUE,
    username      VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    student_id    VARCHAR(20)  NOT NULL UNIQUE, -- 학번
    PRIMARY KEY (id)
);

-- Instructor 테이블
CREATE TABLE instructors
(
    id            BIGINT       NOT NULL,
    user_id       VARCHAR(50)  NOT NULL UNIQUE,
    username      VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    major_subject VARCHAR(100) NOT NULL, -- 전공 과목
    PRIMARY KEY (id)
);

-- Admin 테이블
CREATE TABLE admins
(
    id            BIGINT       NOT NULL,
    user_id       VARCHAR(50)  NOT NULL UNIQUE,
    username      VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    position      VARCHAR(100) NOT NULL, -- 직책
    department    VARCHAR(100) NOT NULL, -- 부서
    PRIMARY KEY (id)
);

-- 인덱스 추가
CREATE INDEX idx_students_email ON students (email);
CREATE INDEX idx_students_user_id ON students (user_id);
CREATE INDEX idx_students_student_id ON students (student_id);

CREATE INDEX idx_instructors_email ON instructors (email);
CREATE INDEX idx_instructors_user_id ON instructors (user_id);

CREATE INDEX idx_admins_email ON admins (email);
CREATE INDEX idx_admins_user_id ON admins (user_id);