package com.devyujin.jdbc.service;

import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.model.Role;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 인스턴스를 클래스 단위로 유지
class RoleServiceTest {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceTest.class);
    private Connection connection;
    private RoleService roleService;

    private static final int TEST_ROLE_ID = 100;
    private static final String TEST_ROLE_NAME = "TEST_ADMIN";

    @BeforeEach
    void setUp() {
        try {
            connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false); // 트랜잭션 롤백을 위한 auto-commit 비활성화
            roleService = new RoleService(connection);

            // Arrange: 테스트 데이터 삽입
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO roles (role_id, role_name) VALUES (?, ?)"
            );
            ps.setInt(1, TEST_ROLE_ID);
            ps.setString(2, TEST_ROLE_NAME);
            ps.executeUpdate();
            log.info("테스트용 역할 추가 완료");
        } catch (SQLException e) {
            // 테스트가 실패해야 하는 경우 명시적으로 실패를 발생시키는 데 사용
            Assertions.fail("DB 연결 실패: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("역할 조회 테스트 (정상 케이스)")
    void testGetRoleById() {
        // Act
        Role role = null;
        try {
            role = roleService.getRoleById(TEST_ROLE_ID);
        } catch (SQLException e) {
            Assertions.fail("SQL 실행 오류 발생: " + e.getMessage());
        }

        // Assert
        Assertions.assertNotNull(role, "역할이 존재해야 합니다.");
        Assertions.assertEquals(TEST_ROLE_ID, role.getRoleId(), "ID가 일치해야 합니다.");
        Assertions.assertEquals(TEST_ROLE_NAME, role.getRoleName(), "역할명이 일치해야 합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 역할 조회 테스트 (예외 발생)")
    void testGetRoleById_NotFound() {
        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            roleService.getRoleById(9999); // 존재하지 않는 ID 조회
        });

        Assertions.assertEquals("해당 ID의 역할을 찾을 수 없습니다.", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        try {
            connection.rollback(); // 테스트 후 데이터 롤백
            connection.setAutoCommit(true); // 원래 상태로 변경
            connection.close();
            log.info("테스트 종료 후 데이터 롤백 완료");
        } catch (SQLException e) {
            log.error("테스트 종료 중 오류 발생: " + e.getMessage());
        }
    }
}