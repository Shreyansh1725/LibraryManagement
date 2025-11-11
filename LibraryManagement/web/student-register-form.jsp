<%-- 
    Document   : student-register-form
    Created on : Nov 7, 2025, 11:56:01 PM
    Author     : Shreyansh Bhaliya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student Registration</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
        div { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="password"] { width: 100%; padding: 8px; box-sizing: border-box; }
        input[type="submit"] { background-color: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .message { padding: 10px; margin-bottom: 15px; border-radius: 4px; }
        .success { background-color: #d4edda; color: #155724; }
        .error { background-color: #f8d7da; color: #721c24; }
    </style>
</head>
<body>

    <h2>Student Registration</h2>
    <p>Create a new account to borrow books.</p>

    <%-- This code will display success or error messages --%>
    <%
        String message = request.getParameter("message");
        String messageType = request.getParameter("type");
        if (message != null) {
            out.println("<div class='message " + (messageType.equals("success") ? "success" : "error") + "'>" + message + "</div>");
        }
    %>

    <form action="StudentRegisterServlet" method="post">
        <div>
            <label>Full Name:</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="Register">
        </div>
    </form>
    <p>Already have an account? <a href="student-login-form.jsp">Login here</a></p>

</body>
</html>
