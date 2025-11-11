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
import javax.servlet.http.HttpSession;

@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get the book ID from the URL parameter
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        
        // 2. Get the student ID from the session
        HttpSession session = request.getSession(false); // Get existing session

        // Check if user is logged in
        if (session == null || session.getAttribute("student_id") == null) {
            response.sendRedirect("student-login-form.jsp?error=Please+login+to+issue+a+book");
            return; // Stop execution
        }
        
        int studentId = (Integer) session.getAttribute("student_id");

        Connection con = null;
        PreparedStatement psIssue = null;
        PreparedStatement psUpdateBook = null;

        try {
            con = DBConnection.getConnection();
            
            // 3. Start a transaction (disable auto-commit)
            con.setAutoCommit(false);

            // 4. Query 1: Insert into issue_details
            String sqlIssue = "INSERT INTO issue_details (book_id, student_id, issue_date, return_status) VALUES (?, ?, CURDATE(), ?)";
            psIssue = con.prepareStatement(sqlIssue);
            psIssue.setInt(1, bookId);
            psIssue.setInt(2, studentId);
            psIssue.setString(3, "pending");
            int issueResult = psIssue.executeUpdate();

            // 5. Query 2: Update the book quantity (decrease by 1)
            String sqlUpdateBook = "UPDATE books SET quantity = quantity - 1 WHERE id = ? AND quantity > 0";
            psUpdateBook = con.prepareStatement(sqlUpdateBook);
            psUpdateBook.setInt(1, bookId);
            int updateResult = psUpdateBook.executeUpdate();

            // 6. Commit the transaction
            // Both queries must be successful (affect 1 row)
            if (issueResult > 0 && updateResult > 0) {
                con.commit(); // Finalize the changes
                response.sendRedirect("ViewBooksServlet?message=Book+Issued+Successfully!");
            } else {
                // One of the queries failed (e.g., quantity was 0)
                con.rollback(); // Undo the changes
                response.sendRedirect("ViewBooksServlet?error=Failed+to+issue+book+(e.g.,+out+of+stock)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Rollback on any SQL error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            response.sendRedirect("ViewBooksServlet?error=Database+Error");
        } finally {
            // 7. Clean up
            try {
                if (psIssue != null) psIssue.close();
                if (psUpdateBook != null) psUpdateBook.close();
                if (con != null) {
                    con.setAutoCommit(true); // Reset auto-commit
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}