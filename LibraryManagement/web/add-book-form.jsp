<%-- 
    Document   : add-book-form
    Created on : Nov 7, 2025, 10:41:41 PM
    Author     : Shreyansh Bhaliya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New Book</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
        div { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="number"] { width: 100%; padding: 8px; box-sizing: border-box; }
        input[type="submit"] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    
    <%-- This code checks if a 'message' was sent in the URL --%>
<%
    String message = request.getParameter("message");
    if (message != null) {
        out.println("<h3 style='color:green;'>" + message + "</h3>");
    }
%>

    <h2>Librarian: Add New Book</h2>
    
    <form action="AddBookServlet" method="post">
        <div>
            <label>Book Title:</label>
            <input type="text" name="title" required>
        </div>
        <div>
            <label>Author:</label>
            <input type="text" name="author" required>
        </div>
        <div>
            <label>Publisher:</label>
            <input type="text" name="publisher" required>
        </div>
        <div>
            <label>Quantity:</label>
            <input type="number" name="quantity" required min="1">
        </div>
        <div>
            <input type="submit" value="Add Book">
        </div>
    </form>

</body>
</html>
