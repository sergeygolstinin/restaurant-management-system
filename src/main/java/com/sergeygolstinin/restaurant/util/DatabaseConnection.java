package com.sergeygolstinin.restaurant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ResManSystem";
    private static final String USER = "RestManagment";
    private static final String PASSWORD = "qawsedrf";

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    /**
     * Get a connection to the database.
     * @return a Connection object
     */
    public static Connection getConnection() {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Create and return a connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Convert checked exceptions to unchecked exceptions for simplicity
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
