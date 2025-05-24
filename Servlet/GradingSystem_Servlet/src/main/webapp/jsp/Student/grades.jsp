<%@ page import="model.Grade" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<ul>
    <%
        List<Grade> grades = (List<Grade>) session.getAttribute("grades");
        if (grades != null && !grades.isEmpty()) {
            for (Grade grade : grades) {
    %>
    <li><b><%= grade.getCourse().getName() %></b>: <%= grade.getGrade() %></li>
    <%
            }
        }
    %>
</ul>
</body>
</html>
