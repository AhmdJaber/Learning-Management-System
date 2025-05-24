// src/components/StudentActions.js

import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const StudentActions = () => {
    const [action, setAction] = useState(null);
    const [grades, setGrades] = useState([]);
    const [session] = useState({
        student: true,
        hide: false,
        studentID: 1,  
    });
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('studentToken');
        if (!token) {
            navigate('/student/login');
        }
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const actionValue = e.target.elements.action.value;

        if (actionValue === 'getAllGrades') {
            try {
                console.log(`Fetching grades for student ID: ${session.studentID}`);
                const response = await fetch(`http://localhost:8080/student/getAllGrades/${session.studentID}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('studentToken')}`, // Use student token
                    }
                });

                if (response.ok) {
                    const data = await response.json(); // Parse the response as JSON
                    setGrades(data);
                } else {
                    console.error('Failed to fetch grades, status:', response.status);
                    setGrades([]); // Reset grades on failure
                }
            } catch (error) {
                console.error('Error fetching grades:', error);
                setGrades([]); // Reset grades on error
            }
        }

        setAction(actionValue);
    };

    const handleLogout = () => {
        alert("Logged out from student");
        localStorage.removeItem('studentToken');
        navigate('/main');
    };

    return (
        <div class="container">
            <h1>Student:</h1>
            <h4>Make Action:</h4>

            <form onSubmit={handleSubmit}>
                <button type="submit" name="action" value="getAllGrades">
                    View all enrolled courses
                </button>
            </form>
            <br />

            {action === 'getAllGrades' && grades.length > 0 && (
                <div>
                    <h2>Enrolled Courses:</h2>
                    <ul>
                        {grades.map((course) => (
                            <li key={course.id}>{course.name}</li>
                        ))}
                    </ul>
                </div>
            )}

            <br />
            <button onClick={handleLogout}>Log out</button>
            <br /><br />
            <button onClick={() => navigate('/main')}>Home</button>
        </div>
    );
};

export default StudentActions;
