package com.devyujin.jdbc.service;

import com.devyujin.jdbc.dao.UserDao;
import com.devyujin.jdbc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao;
    private final Connection connection;

    /**
     * 📌 생성자 주입 (의존성 주입)
     */
    public UserService(Connection connection) {
        this.connection = connection;
        this.userDao = new UserDao(connection);
    }


    /**
     * 📌 모든 사용자 조회
     * - 데이터 검증 후 반환
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();

        if(users == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return userDao.getAllUsers();
    }

    /**
     * 📌 단일 사용자 조회
     *
     * 이 메서드는 주어진 사용자 ID를 기반으로 데이터베이스에서 사용자를 조회합니다.
     *
     * @param userId 사용자의 고유 식별자(ID)를 전달 받는 매개변수
     * @return 조회된 사용자 객체를 반환합니다. 사용자가 존재하지 않을 경우, 예외가 발생합니다.
     * @throws IllegalArgumentException 해당 ID의 사용자가 존재하지 않을 경우 발생합니다.
     * @throws SQLException 데이터베이스 접근 중 오류가 발생할 경우 발생합니다.
     */
    public User getUserById(int userId) throws SQLException {
        User user = userDao.getUserById(userId);

        if (user == null) {
            throw new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        return user;
    }


    /**
     * 📌 사용자 등록 (CREATE)
     * - 이메일 중복 체크 후 추가
     * @param user 사용자 객체를 전달받음
     * @return boolean 성공여부를 boolean 타입으로 반환
     * @throws SQLException 데이터베이스 접근 중 오류가 발생할 경우 발생합니다.
     * @throws IllegalArgumentException 중복하는 이메일이 존재하는 경우 발생
     */
    public boolean registerUser(User user) throws SQLException {
        // 중복 이메일 검사
        List<User> existingUsers = getAllUsers();
        for (User u : existingUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
            }
        }
        return userDao.addUser(user);
    }


    /**
     * 📌 사용자 정보 수정 (UPDATE)
     * - 기존 사용자 존재 여부 확인 후 업데이트
     * - 이메일 중복 여부 검증 추가 (자신의 이메일은 제외)
     *
     * @param user 수정할 사용자 정보를 포함하는 User 객체
     * @return boolean 사용자 정보 수정 성공 여부를 반환합니다.
     * @throws IllegalArgumentException 수정할 사용자가 존재하지 않거나, 이메일이 이미 존재하는 경우 발생합니다.
     * @throws SQLException 데이터베이스 접근 중 오류가 발생할 경우 발생합니다.
     */
    public boolean updateUser(User user) throws SQLException {
        // 1️⃣ 기존 사용자 존재 여부 확인
        User existingUser = getUserById(user.getUserId());

        if (existingUser == null) {
            throw new IllegalArgumentException("수정할 사용자를 찾을 수 없습니다.");
        }

        // 이메일 존재하는지 확인
        // 아래의 경우 비효율 적이며 email을 조회하는 로직을 추가하는게 더욱 효율적임.
        List<User> existingUsers = getAllUsers();
        for (User u : existingUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
            }
        }

        boolean result = userDao.updateUser(user);
        if (!result) {
            throw new SQLException("수정하는 과정에서 오류가 발생되었습니다.");
        }

        // 3️⃣ 업데이트 수행
        return result;
    }


    /**
     * 📌 사용자 삭제 (DELETE)
     * - 삭제 시 확인 메시지 출력 후 진행
     *
     * @param userId 삭제할 사용자의 고유 ID
     * @return boolean 삭제 성공 여부를 반환합니다.
     * @throws IllegalArgumentException 삭제할 사용자가 존재하지 않는 경우 발생합니다.
     * @throws SQLException 데이터베이스 접근 중 오류가 발생할 경우 발생합니다.
     */
    public boolean deleteUser(int userId) throws SQLException {
        User existingUser = getUserById(userId);
        if (existingUser == null) {
            throw new IllegalArgumentException("삭제할 사용자를 찾을 수 없습니다.");
        }
        return userDao.deleteUser(userId);
    }

}