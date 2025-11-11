/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.library.controller;

import com.library.util.DBConnection; // Import our connection class
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// This annotation is the "address" that the form's "action" attribute points to
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

    // The form uses method="post", so we use the doPost() method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Get data from the HTML form
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        int quantity = Integer.parseInt(request.getParameter("quantity")); // Convert string to int

        Connection con = null;
        PreparedStatement ps = null;
        
        // We'll use this to send a success/error message back to the page
        PrintWriter out = response.getWriter();
        
        try {
            // 2. Get the database connection from our utility class
            con = DBConnection.getConnection();
            
            // 3. Create the SQL query
            // We use '?' as placeholders to prevent SQL injection attacks
            String sql = "INSERT INTO books (title, author, publisher, quantity) VALUES (?, ?, ?, ?)";
            
            ps = con.prepareStatement(sql);
            
            // 4. Set the values for the '?' placeholders
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, quantity);
            
            // 5. Execute the query
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                // Success! Redirect back to the form page with a success message.
                response.sendRedirect("add-book-form.jsp?message=Book+Added+Successfully!");
            } else {
                // Fail
                response.sendRedirect("add-book-form.jsp?message=Error+Adding+Book");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("add-book-form.jsp?message=Database+Error");
        } finally {
            // 6. Clean up resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}