package com.sergeygolstinin.restaurant.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
    
    public void someDatabaseOperation() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM some_table");
            while (rs.next()) {
                // process result set
                // Example: System.out.println(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }

    private void handleDatabaseError(SQLException e) {
        // Implement more comprehensive error handling
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        Throwable t = e.getCause();
        while (t != null) {
            System.err.println("Cause: " + t);
            t = t.getCause();
        }
    }
}
