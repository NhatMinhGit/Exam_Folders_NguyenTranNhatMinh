package org.example.cgoc0824m1nguyentrannhatminhmodule3.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/ProductDB?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "123456789";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}
