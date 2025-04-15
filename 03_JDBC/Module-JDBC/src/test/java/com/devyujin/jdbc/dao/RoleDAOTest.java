package com.devyujin.jdbc.dao;

import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.model.Role;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*
 * RoleDAO test
 * - roleDAO의 getRoleBy ID 메서드 테스트
 * */
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //클래스 단위로 인스턴스 유지
class RoleDaoTest {


    private Connection con;
    private RoleDao dao;

    /*
     * 테스트 전 실행되는 설정
     * -connection 객체 생성 및 트랜잭션 시작
     * - 테스트용 데이터 삽입
     * */
    @BeforeEach
    void setUp() {
        try{
            con = JDBCConnection.getConnection();
            dao = new RoleDao(con);
        }catch (SQLException e) {
            Assertions.fail("데이터 베이스 연결 실패");
        }
    }



    /**
     * 📌 Role ID로 역할 조회 테스트
     * - AAA 패턴 적용 (Arrange, Act, Assert)
     */
    @Test
    @DisplayName("Role ID로 역할 조회")
    void getRoleByIdTest() {
        // Arrange (준비) → BeforeEach에서 데이터 삽입 완료

        // Act (실행)
        Role role = dao.getRoleById(1);
        System.out.println("🔍 조회된 역할: " + role);

        // Assert (검증)
        Assertions.assertNotNull(role, "조회된 Role 객체가 null이 아니어야 합니다.");
        Assertions.assertEquals(1, role.getRoleId(), "조회된 역할의 ID가 삽입한 ID와 일치해야 합니다.");
        Assertions.assertEquals("Admin", role.getRoleName(), "조회된 역할의 이름이 일치해야 합니다.");
    }


    /**
     * 📌 테스트 후 롤백하여 데이터 복구 (AfterEach)
     */
    @AfterEach
    void tearDown() {
        try {
            con.close(); // 연결 닫기
            System.out.println("🔄 테스트 데이터 롤백 완료");
        } catch (SQLException e) {
            Assertions.fail("❌ 트랜잭션 롤백 실패: " + e.getMessage());
        }
    }
}