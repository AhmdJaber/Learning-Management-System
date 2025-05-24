import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const TeacherActions = () => {
    const [action, setAction] = useState(null);   
    const [courses, setCourses] = useState([]);   
    const [courseID, setCourseID] = useState(''); 
    const [students, setStudents] = useState([]);  
    const [updated, setUpdated] = useState(null);  
    const navigate = useNavigate();


    useEffect(() => {
        const token = localStorage.getItem('teacherToken');
        if (!token) {
            navigate('/teacher/login');
        }
    }, [navigate]);

    const handleGetCourses = async () => {
        setAction('getAllCourses');
        try {
            const response = await fetch('http://localhost:8080/teacher/allCourses/1', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('teacherToken')}`   
                },
            });
    
            if (!response.ok) {
                throw new Error('Failed to fetch courses');
            }
    
            const coursesData = await response.json();
            setCourses(coursesData);
        } catch (error) {
            console.error('Error fetching courses:', error);
        }
    };

    const handleUpdateGrades = () => {
        setAction('updateGrades');
    };

    const handleGetStudents = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/teacher/allStudentsOfSomeCourse/${courseID}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('tacherTocket')}` 
                }

            });
            const studentsData = await response.json();
            setStudents(studentsData);
        } catch (error) {
            console.error('Error fetching students:', error);
        }
    };

    const handleCourseIDChange = (e) => {
        setCourseID(e.target.value);
    };

    const handleLogout = () => {
        alert("Logged out from teacher"); 
        localStorage.removeItem('teacherToken'); 
        navigate('/main'); 
    };

    const handleUpdateSubmit = async (event) => {
        event.preventDefault();
    
        const studentsWithGrades = students.map(student => ({
            id: student.id,
            grade: document.getElementById(`grade-${student.id}`).value  
        }));
    
        try {
            const response = await fetch(`http://localhost:8080/teacher/updateGrades/${courseID}`, {
                method: 'POST',
                headers: { 
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${localStorage.getItem('tacherTocket')}`
                },
                body: JSON.stringify(studentsWithGrades)   
            });
    
            if (response.ok) {
                setUpdated(true);
            } else {
                setUpdated(false);
            }
        } catch (error) {
            console.error('Error updating grades:', error);
            setUpdated(false);
        }
    };

    return (
        <div class="container">
            <h1>Teacher:</h1>
            <h4>Make Action:</h4>

            <button onClick={handleGetCourses}>Get all associated courses</button> <br /><br />

            {action === 'getAllCourses' && courses.length > 0 && (
                <div>
                    <ul>
                        {courses.map(course => (
                            <li key={course.id}> <b>{course.id}</b> {course.name}</li>
                        ))}
                    </ul>
                </div>
            )}

            <button onClick={handleUpdateGrades}>Update grades for a course</button> <br /><br />

            {action === 'updateGrades' && (
                <div>
                    <form onSubmit={handleGetStudents}>
                        <label>
                            Course ID:
                            <input type="text" value={courseID} onChange={handleCourseIDChange} placeholder="Enter Course ID" required />
                        </label> <br /><br />
                        <button type="submit">Get students for this course</button>
                    </form>
                    <br />
                </div>
            )}

            {students.length > 0 && (
                <div>
                    <h4>Enter the grades for the students</h4>
                    <form onSubmit={handleUpdateSubmit}>

                        <ul>
                            {students.map(student => (
                                <div key={student.id}>
                                    <label>
                                        <li><b>[{student.id}]</b> {student.name} <input type="number" id={`grade-${student.id}`} placeholder="Enter grade"/></li>
                                        <br />
                                    </label>
                                </div>
                            ))}
                        </ul>
                        <br />
                        <button type="submit">Submit Grades</button>
                    </form>
                        <br />
                </div>
            )}

            <button onClick={handleLogout}>Log out</button> <br /><br />

            <button onClick={() => window.location.href = '/main'}>Home</button>

            {updated === true && <h4 style={{ color: 'green' }}>Updated Successfully!</h4>}
            {updated === false && <h4 style={{ color: 'red' }}>Something went wrong while updating!</h4>}
        </div>
    );
};

export default TeacherActions;