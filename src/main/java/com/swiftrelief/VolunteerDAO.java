package com.swiftrelief;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolunteerDAO {

    public static void insertVolunteer(Volunteer v) throws SQLException {
        String sql = "INSERT INTO volunteers (name, age, contact, location, skill, availability) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, v.getName());
            ps.setInt(2, v.getAge());
            ps.setString(3, v.getContact());
            ps.setString(4, v.getLocation());
            ps.setString(5, v.getSkill());
            ps.setBoolean(6, v.isAvailability());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    v.setId(keys.getInt(1));
                }
            }
        }
    }

    public static List<Volunteer> getAllVolunteers() throws SQLException {
        List<Volunteer> list = new ArrayList<>();
        String sql = "SELECT * FROM volunteers ORDER BY id DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Volunteer v = new Volunteer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("contact"),
                        rs.getString("skill"),
                        rs.getString("location"),
                        rs.getBoolean("availability")
                );
                list.add(v);
            }
        }
        return list;
    }

    public static void deleteVolunteer(int id) throws SQLException {
        String sql = "DELETE FROM volunteers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
