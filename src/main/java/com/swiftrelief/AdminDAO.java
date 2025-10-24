package com.swiftrelief;

import java.sql.*;

public class AdminDAO {

    public boolean validateAdmin(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAdmin(String username, String password) {
        String query = "INSERT INTO admins (username, password) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding admin: " + e.getMessage());
            return false;
        }
    }
}
