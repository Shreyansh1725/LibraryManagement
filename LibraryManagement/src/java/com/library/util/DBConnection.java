/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // 1. Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Establish the connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Print the error for debugging
        }
        return con;
    }
}