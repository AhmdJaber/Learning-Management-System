<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Student Grading System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            margin: 0;
            padding: 20px;
            overflow-y: auto;
        }

        .container {
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
            max-width: 600px;
            width: 100%;
            margin: 20px 0;
        }

        h1 {
            margin-bottom: 10px;
            color: #333;
        }

        h4 {
            margin-bottom: 20px;
            color: #555;
        }

        button {
            background-color: #7e7e7e;
            color: white;
            padding: 15px 60px;
            margin: 15px 0;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            max-width: 250px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #cccccc;
            color: black;
        }

        button:focus {
            outline: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Teacher:</h1>
    <h4>Make Action:</h4>

    <form action="teacher" method="get">
        <button type="submit" name="action" value="getAllCourses">Get all associated courses</button>
    </form> <br>

    <%
        String action = request.getParameter("action");
        if (action != null && action.equals("getAllCourses")) {
            if ((Boolean) session.getAttribute("hideCourses")){
                %>
            <%@include file="courses.jsp"%>
    <%
            }
        }

    %>

    <form action="teacher" method="get">
        <button type="submit" name="action" value="updateGrades">Update grades for a course</button>
    </form> <br>


    <%

        if (action != null && action.equals("updateGrades")) {
    %>
    <form action="teacher" method="get">
        <label>Course ID:
            <input type="text" name="courseID" placeholder="Enter Course ID" required>
        </label> <br> <br>
        <button type="submit" name="getStudents" value="updateGrades">Get students for this course</button>
    </form>
    <br>
    <%
        }

    %>

    <%
        Boolean getStudents = (Boolean) session.getAttribute("showStudents");
        if (getStudents != null && getStudents) {
            if ((Boolean) session.getAttribute("hideStudents")){
    %>
    <h4>Enter the grades for the students</h4>
    <%@include file="students.jsp"%>
    <%
            }
        }
    %>

    <%
        if (session.getAttribute("teacher") != null) {
    %>
    <form action="teacher" method="get">
        <button type="submit" name="exit" value="logout">Log out</button>
    </form> <br>
    <%
        }
    %>

    <form action="teacher" method="get">
        <button type="submit" name="exit" value="exit">Home</button>
    </form>

    <%
        Boolean updated = (Boolean) session.getAttribute("updated");
        if (updated != null && updated) {
    %>
    <h4 style="color: green">updated Successfully!</h4>
    <%
    }
    else if (updated != null) {
    %>
    <h4 style="color: red">Something went wrong while creating!</h4>
    <%
        }
    %>
</div>
</body>
</html>
