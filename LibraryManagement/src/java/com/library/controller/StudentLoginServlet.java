/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.library.controller;

import com.library.util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get data from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            
            // 2. Create the SQL query to find the student
            String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            // 3. Execute the query
            rs = ps.executeQuery();

            // 4. Check if a matching student was found
            if (rs.next()) {
                // SUCCESS! User exists.
                
                // Get student details from the result set
                int studentId = rs.getInt("id");
                String studentName = rs.getString("name");
                
                // 5. Create a new HTTP Session
                HttpSession session = request.getSession();
                
                // 6. Store student's ID and Name in the session
                // This "marks" them as logged in
                session.setAttribute("student_id", studentId);
                session.setAttribute("student_name", studentName);

                // 7. Redirect to the student's dashboard (we'll create this next)
                response.sendRedirect("student-dashboard.jsp");
                
            } else {
                // FAILURE! No user found with that email/password.
                
                // 8. Redirect back to the login form with an error message
                response.sendRedirect("student-login-form.jsp?error=Invalid+email+or+password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("student-login-form.jsp?error=Database+error");
        } finally {
            // 9. Clean up resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}