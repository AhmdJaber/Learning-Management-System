<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<html>
<body>
<h5>
</h5>
<form action="teacher" method="post">
    <%
        List<Student> students = (List<Student>) session.getAttribute("students");
        if (students != null && !students.isEmpty()) {
            for (Student student : students) {
    %>
    <li>
        <b><%= student.getId() %></b> <%= student.getName() %>
        <input type="number" name="studentGrade<%= student.getId() %>" placeholder="grade for <%= student.getName() %>">
    </li>
    <%
            }
        }
    %>
    <br>
    <button type="submit" name="updateStudentGrades" value="update">Update</button>
</form>
</body>
</html>
