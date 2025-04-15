package com.devyujin.jdbc.dao;

import com.devyujin.jdbc.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao {
    private final Connection connection;

    public RoleDao(Connection connection) {
        this.connection = connection;
    }

    public Role getRoleById(int roleId) {
        String query = "SELECT * FROM roles WHERE role_id = ?";
        Role role = null;

        /*
        * PerpearedStatement
        * - JDBC API의 일부로, SQL 쿼리를 실행하는데 사용된다.
        * - Statement와 달리 매개변수화된 쿼리를 사용할 수 있다.
        * */
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roleId);
            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    role = new Role(
                            rs.getInt("role_id"), // select절의 컬럼명으로, 별칭이 있다면 별칭으로
                            rs.getString("role_name")
                    );
                }
            }
        } catch(SQLException e) {
            System.out.println("getRoleById() 실행 중 오류 발생");
            e.printStackTrace();
        }

        return role;
    }
}
