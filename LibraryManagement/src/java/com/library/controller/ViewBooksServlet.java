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
import java.util.ArrayList; // We need this to create a list
import java.util.List; // We need this for the List type

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// A simple helper class to hold book data
// We can put this inside the same file for simplicity
class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;

    // Constructor
    public Book(int id, String title, String author, String publisher, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getQuantity() { return quantity; }
}


@WebServlet("/ViewBooksServlet")
public class ViewBooksServlet extends HttpServlet {

    // This time, the user is just "getting" the page, so we use doGet()
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM books";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Loop through all the results from the database
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int quantity = rs.getInt("quantity");
                
                // Add a new Book object to our list
                bookList.add(new Book(id, title, author, publisher, quantity));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 1. Set the bookList as an attribute to be sent to the JSP
        request.setAttribute("bookList", bookList);

        // 2. Forward the request (and the bookList) to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-books.jsp");
        dispatcher.forward(request, response);
    }
}