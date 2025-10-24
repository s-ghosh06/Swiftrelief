package com.swiftrelief;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/swiftrelief_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Ghosh06"; // <-- your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            System.out.println("[DBUtil] Attempting to connect to database...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[DBUtil] Connection established successfully.");
        } catch (SQLException e) {
            System.err.println("[DBUtil] Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
}