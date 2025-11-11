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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Helper class to hold the *joined* data
class IssuedBook {
    private String title;
    private String author;
    private String issueDate;
    private String returnStatus;

    public IssuedBook(String title, String author, String issueDate, String returnStatus) {
        this.title = title;
        this.author = author;
        this.issueDate = issueDate;
        this.returnStatus = returnStatus;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIssueDate() { return issueDate; }
    public String getReturnStatus() { return returnStatus; }
}


@WebServlet("/ViewMyBooksServlet")
public class ViewMyBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Check if student is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student_id") == null) {
            response.sendRedirect("student-login-form.jsp?error=Please+login+first");
            return;
        }

        int studentId = (Integer) session.getAttribute("student_id");
        List<IssuedBook> myBooks = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            
            // 2. This query JOINS three tables to get the data
            String sql = "SELECT b.title, b.author, i.issue_date, i.return_status " +
                         "FROM issue_details i " +
                         "JOIN books b ON i.book_id = b.id " +
                         "WHERE i.student_id = ?";
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            
            rs = ps.executeQuery();

            // 3. Loop through the results
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String issueDate = rs.getString("issue_date");
                String returnStatus = rs.getString("return_status");
                
                myBooks.add(new IssuedBook(title, author, issueDate, returnStatus));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. Clean up
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 5. Send the list to the JSP page
        request.setAttribute("myBooksList", myBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-my-books.jsp");
        dispatcher.forward(request, response);
    }
}