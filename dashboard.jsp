<%@ page import="java.util.*,Course" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= username %>!</h2>
    <form action="LogoutServlet" method="get">
        <input type="submit" value="Logout">
    </form>

    <h3>Available Courses</h3>
    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color: green;"><%= message %></p>
    <%
        }
    %>
    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Instructor</th><th>Action</th></tr>
        <%
            for (Course c : courses) {
        %>
            <tr>
                <td><%= c.getCourseId() %></td>
                <td><%= c.getCourseName() %></td>
                <td><%= c.getInstructor() %></td>
                <td><a href="EnrollServlet?courseId=<%= c.getCourseId() %>">Enroll</a></td>
            </tr>
        <%
            }
        %>
    </table>

    <h3>Enrolled Courses</h3>
    <%
        List<Course> enrolled = (List<Course>) request.getAttribute("enrolledCourses");
        if (enrolled != null && !enrolled.isEmpty()) {
    %>
        <ul>
        <%
            for (Course c : enrolled) {
        %>
            <li><%= c.getCourseName() %> - <%= c.getInstructor() %></li>
        <%
            }
        %>
        </ul>
    <%
        } else {
    %>
        <p>No courses enrolled yet.</p>
    <%
        }
    %>
</body>
</html>
