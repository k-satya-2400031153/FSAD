package com.fsads3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCCreate {
    public static void main(String[] args) {
        // It's common to include connection options to avoid SSL/timezone warnings on recent MySQL versions
        String url = "jdbc:mysql://localhost:3306/spark";
        String username = "root";
        String password = "KsatyaMySQL@2006@gmail.com";
        String createTableQuery = "create table if not exists Student(" +
                "id int primary key auto_increment," +
                "name varchar(20)" +
                ")";

        try {
            System.out.println("Connecting to database...");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ignored) {
                // Driver auto-loading should work via SPI when dependency is present
            }

            // Use try-with-resources to ensure proper cleanup
            try (Connection con = DriverManager.getConnection(url, username, password);
                 Statement st = con.createStatement()) {
                System.out.println("Connection Successful!");
                st.executeUpdate(createTableQuery);
                System.out.println("Table 'Student' created successfully (or already exists).");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}