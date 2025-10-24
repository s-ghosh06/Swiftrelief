package com.swiftrelief;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RequestDAO {

    public static void addRequest(Request req) {
        String sql = "INSERT INTO requests (requester_name, contact, location, resource_needed, quantity, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, req.getRequesterName());
            ps.setString(2, req.getContact());
            ps.setString(3, req.getLocation());
            ps.setString(4, req.getResourceNeeded());
            ps.setInt(5, req.getQuantity());
            ps.setString(6, req.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Request> getAllRequests() {
        ObservableList<Request> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM requests";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Request(
                        rs.getInt("id"),
                        rs.getString("requester_name"),
                        rs.getString("contact"),
                        rs.getString("location"),
                        rs.getString("resource_needed"),
                        rs.getInt("quantity"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteRequest(int id) {
        String sql = "DELETE FROM requests WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
