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
    <h1>Super Admin</h1>
    <h4>Make Action:</h4>

    <form action="super" method="get">
        <button type="submit" name="action" value="createAdmin">Create a new Admin</button>
    </form>

    <%
        Boolean created = (Boolean) session.getAttribute("created");

        if (created != null && created) {
    %>
    <h4 style="color: green">
        Admin Created
    </h4>
    <%
        session.removeAttribute("created");
    }
    else {
    %>
    <br>
    <%
        }
    %>

    <%
        String action = request.getParameter("action");

        if (Objects.equals(action, "createAdmin")) {
    %>
    <form action="super" method="post">
        <br>
        <label>Username:
            <input type="text" name="name" placeholder="Enter Admin Name" required>
        </label>
        <br><br>
        <label>Password:
            <input type="password" name="password" placeholder="Enter Admin Password" required>
        </label>
        <br><br>
        <button type="submit" name="action" value="create">Submit Admin</button>
    </form>
    <%
        }
    %>

    <%
        if (session.getAttribute("super") != null) {
    %>
    <form action="super" method="get">
        <button type="submit" name="exit" value="logout">Log out</button>
    </form> <br>
    <%
        }
    %>

    <form action="super" method="get">
        <button type="submit" name="exit" value="exit">Home</button>
    </form>
</div>
</body>
</html>

