<%@ page import="model.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<ul>
    <%
        List<Course> courses = (List<Course>) session.getAttribute("courses");
        if (courses != null && !courses.isEmpty()) {
            for (Course course : courses) {
    %>
    <li><b><%= course.getId() %></b> <%= course.getName() %></li>
    <%
            }
        }
    %>
</ul>
</body>
</html>
