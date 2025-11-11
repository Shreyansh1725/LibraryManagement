<%-- 
    Document   : view-books
    Created on : Nov 7, 2025, 11:48:40 PM
    Author     : Shreyansh Bhaliya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- This line imports the JSTL "core" library, which lets us use loops --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View All Books</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; } /* Zebra striping */
        .nav-links { margin-bottom: 20px; }
        .nav-links a { margin-right: 15px; text-decoration: none; font-size: 1.1em; }
    </style>
</head>
<body>
    
    <%-- Code to display issue success/error messages --%>
    <%
        String message = request.getParameter("message");
        String error = request.getParameter("error");
        
        if (message != null) {
            out.println("<h3 style='color:green;'>" + message + "</h3>");
        }
        if (error != null) {
            out.println("<h3 style='color:red;'>" + error + "</h3>");
        }
    %>

    <h2>Library Book Catalog</h2>
    
    <div class="nav-links">
        <a href="add-book-form.jsp">Add New Book</a>
    </div>

   <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Publisher</th>
            <th>Quantity</th>
            <th>Action</th>  <%-- 1. ADDED THIS HEADER --%>
        </tr>
        
        <c:forEach var="book" items="${bookList}">
            <tr>
                <%-- ${book.id} uses Expression Language (EL) to call the getId() method --%>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.publisher}</td>
                <td>${book.quantity}</td>
                
                <%-- 2. ADDED THIS CELL --%>
                <td>
                    <%-- 
                      This link goes to a new servlet and passes the book's ID 
                      in the URL (e.g., ?book_id=3) 
                    --%>
                    <a href="IssueBookServlet?book_id=${book.id}">Issue Book</a>
                </td>
            </tr>
        </c:forEach>
        
    </table>

</body>
</html>