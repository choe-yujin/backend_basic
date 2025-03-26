package com.devyujin.jdbc.dao;

import com.devyujin.jdbc.model.User;
import com.devyujin.jdbc.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 📌 UserDao (XML 기반 SQL 로딩)
 * - QueryUtil을 통해 XML에서 SQL 쿼리를 로드하여 실행
 * - Connection을 생성자로 주입받아 의존성을 줄임
 */
public class UserDao {

    private final Connection connection;

    /**
     * 📌 생성자를 통해 Connection 객체 주입
     */
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * 📌 모든 사용자 조회 (READ)
     * - XML에서 `getAllUsers` 쿼리를 가져와 실행
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllUsers"); // XML에서 쿼리 로드

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getInt("role_id"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 📌 단일 사용자 조회 (READ)
     */
    public User getUserById(int userId) {
        String query = QueryUtil.getQuery("getUserById");
        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getInt("role_id"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }




    /**
     * 📌 사용자 추가 (CREATE)
     */
    public boolean addUser(User user) {
        String query = QueryUtil.getQuery("addUser");

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 📌 사용자 수정 (UPDATE)
     */
    public boolean updateUser(User user) {
        String query = QueryUtil.getQuery("updateUser");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleId());
            ps.setInt(5, user.getUserId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 📌 사용자 삭제 (DELETE)
     */
    public boolean deleteUser(int userId) {
        String query = QueryUtil.getQuery("deleteUser");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}