/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    // This can be a doGet() because it's just a simple link
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Get the current session
        HttpSession session = request.getSession(false); // 'false' means don't create a new one if it doesn't exist
        
        if (session != null) {
            // 2. Invalidate the session (log them out)
            session.invalidate();
        }
        
        // 3. Redirect back to the login page
        // We add a message to show they successfully logged out.
        response.sendRedirect("student-login-form.jsp?message=You+have+been+logged+out.");
    }
}