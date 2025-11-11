<%-- 
    Document   : student-dashboard
    Created on : Nov 8, 2025, 12:07:48 AM
    Author     : Shreyansh Bhaliya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- 
    This is the "Security Guard" for the page.
    It checks if a "student_id" attribute exists in the session.
    If it does NOT exist (== null), it means the user is not logged in.
--%>
<%
    Integer studentId = (Integer) session.getAttribute("student_id");
    String studentName = (String) session.getAttribute("student_name");

    if (studentId == null) {
        // Not logged in. Redirect them back to the login page.
        response.sendRedirect("student-login-form.jsp?error=Please+login+first");
    }
%>
<%-- If the code reaches here, it means the user *is* logged in. --%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .header { background-color: #f4f4f4; padding: 20px; border-radius: 8px; }
        .header h2 { margin: 0; }
        .header p { margin: 5px 0 0; }
        .nav { margin-top: 20px; }
        .nav a { display: inline-block; padding: 10px 15px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; margin-right: 10px; }
        .nav a.logout { background-color: #dc3545; }
    </style>
</head>
<body>

    <div class="header">
        <%-- Display the student's name from the session --%>
        <h2>Welcome, ${student_name}!</h2>
        <p>This is your library dashboard.</p>
    </div>

    <div class="nav">
        <a href="ViewBooksServlet">View & Issue Books</a>
        <a href="view-my-books.jsp">View My Issued Books</a>
        <a href="LogoutServlet" class="logout">Logout</a>
    </div>

</body>
</html>