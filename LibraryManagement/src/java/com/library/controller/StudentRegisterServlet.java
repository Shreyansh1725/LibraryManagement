/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.library.controller;

import com.library.util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentRegisterServlet")
public class StudentRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get data from the registration form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // In a real app, you should HASH this!

        Connection con = null;
        PreparedStatement ps = null;
        
        // Build the redirect URL (to send messages back)
        String redirectURL = "student-register-form.jsp";

        try {
            con = DBConnection.getConnection();
            
            // 2. Create the SQL INSERT query
            String sql = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            
            // 3. Set the values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            
            // 4. Execute the query
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                // Success!
                redirectURL += "?message=Registration+Successful!+You+can+now+login.&type=success";
            } else {
                // Failure
                redirectURL += "?message=Registration+Failed.+Please+try+again.&type=error";
            }

        } catch (SQLException e) {
            // Handle specific errors, like a duplicate email
            if (e.getErrorCode() == 1062) { // 1062 is the MySQL code for 'Duplicate entry'
                redirectURL += "?message=Error:+This+email+is+already+registered.&type=error";
            } else {
                redirectURL += "?message=Database+Error:+Could+not+register.&type=error";
                e.printStackTrace();
            }
        } finally {
            // 5. Clean up
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // 6. Redirect back to the form
        response.sendRedirect(redirectURL);
    }
}