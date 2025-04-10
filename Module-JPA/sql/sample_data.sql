-- 시퀀스 초기화
INSERT INTO hibernate_sequences(sequence_name, next_val)
VALUES ('user_seq', 1)
ON DUPLICATE KEY UPDATE next_val = VALUES(next_val);

-- 샘플 학생 데이터
INSERT INTO students (id, user_id, username, email,
                      password_hash, student_id)
VALUES (1, 'student001', '김민수', 'minsu.kim@example.com',
        '$2a$10$randomhashvalueforpassword1', 'ST2024001'),
       (3, 'student002', '이지원', 'jiwon.lee@example.com',
        '$2a$10$randomhashvalueforpassword2', 'ST2024002');

-- 샘플 강사 데이터
INSERT INTO instructors (id, user_id, username, email,
                         password_hash, major_subject)
VALUES (2, 'instructor001', '강민지', 'minji.kang@example.com',
        '$2a$10$randomhashvalueforpassword3', '웹 개발'),
       (4, 'instructor002', '윤재혁', 'jaehyuk.yoon@example.com',
        '$2a$10$randomhashvalueforpassword4', '데이터 사이언스');

-- 샘플 관리자 데이터
INSERT INTO admins (id, user_id, username, email,
                    password_hash, position, department)
VALUES (5, 'admin001', '김태우', 'taewoo.kim@example.com',
        '$2a$10$randomhashvalueforpassword5', '시스템 관리자', 'IT 운영팀'),
       (6, 'admin002', '정예진', 'yejin.jung@example.com',
        '$2a$10$randomhashvalueforpassword6', '교육 운영 매니저', '교육 관리팀');

-- 비밀번호 해시는 보안을 위해 실제 운영 시 강력한 랜덤 솔트와 해시를 사용해야 함