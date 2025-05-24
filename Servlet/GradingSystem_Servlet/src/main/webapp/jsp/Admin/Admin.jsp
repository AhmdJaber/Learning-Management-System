<%@ page import="java.util.Objects" %>
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
    <h1>Admin:</h1>
    <h4>Make Action:</h4>

    <%
        String createName = request.getParameter("create");
        String placeName = "";
        String placePassword = "";
        if (createName != null) {
            if (createName.equals("createTeacher")){
                placeName = "Teacher Username";
                placePassword = "Teacher Password";
            } else if (createName.equals("createStudent")){
                placeName = "Student Username";
                placePassword = "Student Password";
            } else if (createName.equals("createCourse")){
                placeName = "Course ID";
                placePassword = "Course name";
            } else if (createName.equals("createEnrollment")){
                placeName = "Student ID";
                placePassword = "Course ID";
            } else if (createName.equals("createAssociation")){
                placeName = "Teacher ID";
                placePassword = "Course ID";
            }
        }
    %>
    <form action="admin" method="get">
        <button type="submit" name="create" value="createTeacher">Create a new Teacher</button>
    </form> <br>

    <form action="admin" method="get">
        <button type="submit" name="create" value="createStudent">Create a new Student</button>
    </form> <br>

    <form action="admin" method="get">
        <button type="submit" name="create" value="createCourse">Create a new Course</button>
    </form> <br>

    <form action="admin" method="get">
        <button type="submit" name="create" value="createEnrollment">Create a new Enrollment</button>
    </form> <br>

    <form action="admin" method="get">
        <button type="submit" name="create" value="createAssociation">Create a new Association</button>
    </form> <br>

    <%
        String action = request.getParameter("create");
        String type = "password";

        if (action != null) {
            if (!action.equals("createTeacher") && !action.equals("createStudent")) {
                type = "text";
            }
            System.out.println("jere");

    %>
    <form action="admin" method="post">
        <label><%= placeName %>:
            <input type="text" name="first" placeholder="<%= placeName %>" required>
        </label>
        <br><br>
        <label><%= placePassword %>:
            <input type="<%=type%>" name="second" placeholder="<%= placePassword %>" required>
        </label>
        <br><br>
        <button type="submit" name="action" value="create">Submit Admin Details</button>
    </form>
    <%
        }
    %>

    <%
        if (session.getAttribute("admin") != null) {
    %>
        <form action="admin" method="get">
            <button type="submit" name="exit" value="logout">Log out</button>
        </form> <br>
    <%
        }
    %>

    <form action="admin" method="get">
        <button type="submit" name="exit" value="exit">Home</button>
    </form>

    <%
        Boolean created = (Boolean) session.getAttribute("created");
        if (created != null && created) {
    %>
        <h4 style="color: green">Created Successfully!</h4>
    <%
        }
        else if (created != null) {
    %>
        <h4 style="color: red">Something went wrong while creating!</h4>
    <%
        }
    %>
</div>
</body>
</html>
