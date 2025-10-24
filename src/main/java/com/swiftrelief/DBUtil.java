package com.swiftrelief;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/swiftrelief_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Ghosh06"; // <-- your MySQL password

    public static Connection getConnection() throws SQLException {
        System.out.println("[DBUtil] Connecting to database...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}