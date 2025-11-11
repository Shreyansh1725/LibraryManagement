<%-- 
    Document   : student-login-form
    Created on : Nov 8, 2025, 12:01:32 AM
    Author     : Shreyansh Bhaliya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student Login</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
        div { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="password"] { width: 100%; padding: 8px; box-sizing: border-box; }
        input[type="submit"] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .message { padding: 10px; margin-bottom: 15px; border-radius: 4px; }
        .error { background-color: #f8d7da; color: #721c24; }
        .success { background-color: #d4edda; color: #155724; }
    </style>
</head>
<body>

    <h2>Student Login</h2>

    <%-- This will show a logout message --%>
<%
    String message = request.getParameter("message");
    if (message != null) {
        out.println("<div class='message success'>" + message + "</div>");
    }
    // We also need to add the 'success' style to our <style> block
%>
    
    <%-- This will show a login error message if one is sent --%>
    <%
        String error = request.getParameter("error");
        if (error != null) {
            out.println("<div class='message error'>" + error + "</div>");
        }
    %>

    <form action="StudentLoginServlet" method="post">
        <div>
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>
    <p>No account? <a href="student-register-form.jsp">Register here</a></p>

</body>
</html>