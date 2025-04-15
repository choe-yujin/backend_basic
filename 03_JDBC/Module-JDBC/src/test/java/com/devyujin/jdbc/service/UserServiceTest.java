package com.devyujin.jdbc.service;

import com.devyujin.jdbc.config.JDBCConnection;
import com.devyujin.jdbc.model.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 클래스 단위 인스턴스 유지
class UserServiceTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    private Connection connection;
    private UserService userService;
    private static final String TEST_EMAIL = "testuser@example.com";
    private static final String TEST_USERNAME = "TestUser";

    /**
     * 📌 테스트 전에 DB 연결 및 트랜잭션 설정
     */
    @BeforeEach
    void setUp() throws SQLException {
        connection = JDBCConnection.getConnection();
        connection.setAutoCommit(false); // 자동 커밋 방지 (테스트 종료 후 롤백)
        userService = new UserService(connection);

        // Arrange : 테스트용 사용자 추가
        userService.registerUser(new User(0, TEST_USERNAME, TEST_EMAIL, "password123", 1, null));

        log.info("테스트용 사용자 추가 완료");
    }

    /**
     * 📌 모든 사용자 조회 테스트
     */
    @Test
    @DisplayName("모든 사용자 조회 테스트")
    void testGetAllUsers() throws SQLException {
        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertFalse(users.isEmpty(), "사용자 목록이 비어 있으면 안됩니다.");

        // 컬렉션과 같은 데이터 구조에서 데이터를 쉽게 처리하기 위한 용도의 API
        assertTrue(users.stream()
                        // anyMatch 주어진 조건을 만족하는 요소가 스트림에 하나라도 존재하는지를 확인하는 데 사용
                        .anyMatch(user -> user.getEmail().equals(TEST_EMAIL)),
                "테스트 계정이 존재해야 합니다.");
    }

    /**
     * 📌 단일 사용자 조회 테스트
     */
    @Test
    @DisplayName("단일 사용자 조회 테스트")
    void testGetUserById() throws SQLException {
        // Arrange
        List<User> users = userService.getAllUsers();

        // Optional 객체는 값을 감싸는 컨테이너로, 해당 객체가 값을 가지고 있는지 확인할 수 있는 메서드를 제공한다.
        // 이는 null 참조로 인한 오류를 방지하기 위해 설계된 것으로, 값이 없을 경우에 대한 안전한 처리를 지원한다.
        // Optional을 사용하면 불필요한 null 체크를 줄이고, 코드의 가독성을 높일 수 있다.
        Optional<Integer> userId = users.stream()
                // 같은 값을 가진 데이터만 필터링 한다.
                .filter(user -> user.getEmail().equals(TEST_EMAIL))
                // 사용자의 id를 추출한다.
                .map(User::getUserId)
                // 조건을 만족하는 첫 번째 요소를 반환한다.
                .findFirst();


        // Act
        User user = userService.getUserById(userId.get());

        // Assert
        assertNotNull(user, "사용자가 존재해야 합니다.");
        assertEquals(TEST_EMAIL, user.getEmail(), "이메일이 일치해야 합니다.");
    }

    /**
     * 📌 사용자 등록 테스트 (중복 이메일 확인 포함)
     */
    @Test
    @DisplayName("사용자 등록 테스트")
    void testRegisterUser() throws SQLException {
        // Arrange
        User newUser = new User(0, "NewUser", "newuser@example.com", "password456", 2, null);

        // Act
        boolean isRegistered = userService.registerUser(newUser);
        List<User> users = userService.getAllUsers();

        // Assert
        assertTrue(isRegistered, "사용자 등록이 성공해야 합니다.");
        assertTrue(users.stream().anyMatch(user -> user.getEmail().equals("newuser@example.com")),
                "새로운 사용자가 목록에 있어야 합니다.");
    }

    /**
     * 📌 중복 이메일 방지 테스트
     */
    @Test
    @DisplayName("중복 이메일 방지 테스트")
    void testDuplicateEmailRegistration() {
        // Arrange
        User duplicateUser = new User(0, "DuplicateUser", TEST_EMAIL, "password789", 1, null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(duplicateUser),
                "중복된 이메일 등록 시 예외가 발생해야 합니다.");
    }

    /**
     * 📌 사용자 수정 테스트 (이메일 중복 방지 포함)
     */
    @Test
    @DisplayName("사용자 정보 수정 테스트")
    void testUpdateUser() throws SQLException {
        // Arrange
        List<User> users = userService.getAllUsers();
        int userId = users.get(0).getUserId();
        User updatedUser = new User(userId, "UpdatedUser", "updateduser@example.com", "newpass123", 1, null);

        // Act
        boolean isUpdated = userService.updateUser(updatedUser);
        User fetchedUser = userService.getUserById(userId);

        // Assert
        assertTrue(isUpdated, "사용자 정보 수정이 성공해야 합니다.");
        assertEquals("updateduser@example.com", fetchedUser.getEmail(), "이메일이 변경되어야 합니다.");
    }

    /**
     * 📌 사용자 삭제 테스트
     */
    @Test
    @DisplayName("사용자 삭제 테스트")
    void testDeleteUser() throws SQLException {
        // Arrange
        List<User> users = userService.getAllUsers();
        int userId = users.get(0).getUserId();

        // Act
        boolean isDeleted = userService.deleteUser(userId);
        List<User> updatedUsers = userService.getAllUsers();

        // Assert
        assertTrue(isDeleted, "사용자 삭제가 성공해야 합니다.");
        assertFalse(updatedUsers.stream().anyMatch(user -> user.getUserId() == userId),
                "삭제된 사용자가 목록에 없어야 합니다.");
    }

    /**
     * 📌 테스트 후 DB 롤백하여 데이터 원상복구
     */
    @AfterEach
    void tearDown() {
        try {
            connection.rollback(); // 트랜잭션 롤백
            connection.setAutoCommit(true);
            connection.close();
            log.info("테스트 완료 후 데이터 롤백");
        } catch (SQLException e) {
            log.error("테스트 후 롤백 중 오류 발생", e);
        }
    }
}