package com.swiftrelief;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisasterDAO {

    public void addDisaster(Disaster disaster) {
        String sql = "INSERT INTO disasters (name, type, location, description, severity, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, disaster.getName());
            stmt.setString(2, disaster.getType());
            stmt.setString(3, disaster.getLocation());
            stmt.setString(4, disaster.getDescription());
            stmt.setString(5, disaster.getSeverity());
            stmt.setString(6, disaster.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Disaster> getAllDisasters() {
        List<Disaster> disasters = new ArrayList<>();
        String sql = "SELECT * FROM disasters";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Disaster disaster = new Disaster(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("severity"),
                        rs.getString("status")
                );
                disasters.add(disaster);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disasters;
    }

    public void deleteDisaster(int id) {
        String sql = "DELETE FROM disasters WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
