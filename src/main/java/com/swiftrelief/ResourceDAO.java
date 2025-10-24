package com.swiftrelief;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class ResourceDAO {

    public static ObservableList<Resource> getAllResources() {
        ObservableList<Resource> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM resources";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                int quantity = rs.getInt("quantity");
                String location = rs.getString("location");
                boolean available = rs.getBoolean("available");

                list.add(new Resource(id, type, quantity, location, available));
            }

            System.out.println("✅ Loaded " + list.size() + " resources from DB.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Error fetching resources: " + e.getMessage());
        }

        return list;
    }

    public static void addResource(Resource resource) {
        String query = "INSERT INTO resources (type, quantity, location, available) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, resource.getType());
            ps.setInt(2, resource.getQuantity());
            ps.setString(3, resource.getLocation());
            ps.setBoolean(4, resource.isAvailable());
            ps.executeUpdate();

            System.out.println("✅ Resource added to DB.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteResource(Resource resource) {
        String query = "DELETE FROM resources WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, resource.getId());
            ps.executeUpdate();
            System.out.println("🗑️ Resource deleted from DB.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
