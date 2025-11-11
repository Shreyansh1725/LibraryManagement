<%-- 
    Document   : view-my-books
    Created on : Nov 8, 2025, 1:19:35 AM
    Author     : Shreyansh Bhaliya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
    Security Check: 
    Ensure the user is logged in before showing the page.
--%>
<%
    if (session.getAttribute("student_id") == null) {
        response.sendRedirect("student-login-form.jsp?error=Please+login+first");
        return; // Stop the page from loading
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My Issued Books</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #28a745; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .nav-links { margin-bottom: 20px; }
        .nav-links a { text-decoration: none; font-size: 1.1em; }
    </style>
</head>
<body>

    <div class="nav-links">
        <a href="student-dashboard.jsp">&larr; Back to Dashboard</a>
    </div>

    <h2>My Issued Books</h2>

    <table>
        <tr>
            <th>Book Title</th>
            <th>Author</th>
            <th>Issue Date</th>
            <th>Return Status</th>
        </tr>
        
        <c:forEach var="book" items="${myBooksList}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.issueDate}</td>
                <td>${book.returnStatus}</td>
            </tr>
        </c:forEach>
        
        <c:if test="${empty myBooksList}">
            <tr>
                <td colspan="4" style="text-align:center;">You have not issued any books.</td>
            </tr>
        </c:if>

    </table>

</body>
</html>
