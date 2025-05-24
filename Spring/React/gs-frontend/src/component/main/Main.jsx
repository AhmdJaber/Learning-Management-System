import React from "react";

const MainPage = () => {
    return (
        <div class="container">
            <h1>Student Grading System</h1>
            <h4>Login into:</h4>
            <br />

            <form action="super" method="GET">
                <button type="submit">Super Admin</button>
            </form>
            <br /> 
            <form action="admin" method="GET">
                <button type="submit">Admin</button>
            </form>  
            <br />
            <form action="teacher" method="GET">
                <button type="submit">Teacher</button>
            </form>  
            <br />
            <form action="student" method="GET">
                <button type="submit">Student</button>
            </form>

        </div>
    )
}

export default MainPage