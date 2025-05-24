import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const AdminCreation = () => {
    const [currentAction, setCurrentAction] = useState(null);
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [studentID, setStudentID] = useState('');
    const [teacherID, setTeacherID] = useState('');
    const [isCreated, setIsCreated] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('adminToken');
        if (!token) {
            navigate('/admin/login');
        }
    }, [navigate]);

    const handleCreateTeacher = () => {
        setCurrentAction('createTeacher');
        resetFields();
    };

    const handleCreateStudent = () => {
        setCurrentAction('createStudent');
        resetFields();
    };

    const handleCreateCourse = () => {
        setCurrentAction('createCourse');
        resetFields();
    };

    const handleCreateStudentEnrollment = () => {
        setCurrentAction('createStudentEnrollment');
        resetFields();
    };

    const handleCreateTeacherEnrollment = () => {
        setCurrentAction('createTeacherEnrollment');
        resetFields();
    };

    const handleLogout = () => {
        alert("Logged out from admin");
        localStorage.removeItem('adminToken');
        navigate('/main');
    };

    const resetFields = () => {
        setName('');
        setPassword('');
        setId('');
        setStudentID('');
        setTeacherID('');
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        let endpoint = '';
        let data = {};

        switch (currentAction) {
            case 'createTeacher':
                endpoint = 'http://localhost:8080/admin/createTeacher';
                data = { name, password };
                break;
            case 'createStudent':
                endpoint = 'http://localhost:8080/admin/createStudent';
                data = { name, password };
                break;
            case 'createCourse':
                endpoint = 'http://localhost:8080/admin/createCourse';
                data = { id, name };
                break;
            case 'createStudentEnrollment':
                endpoint = 'http://localhost:8080/admin/createStudentEnrollment';
                data = { studentID, courseID: id };
                break;
            case 'createTeacherEnrollment':
                endpoint = 'http://localhost:8080/admin/createTeacherEnrollment';
                data = { teacherID, courseID: id };
                break;
            default:
                break;
        }

        try {
            const token = localStorage.getItem('adminToken');   
            const response = await fetch(endpoint, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,   
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                setIsCreated(true);
                resetFields();
            } else {
                console.error('Error creating entity');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div class="container">
            <h1>Admin</h1>
            <h5>Make Action: </h5>
            <button onClick={handleCreateTeacher}>Create Teacher</button><br /><br />
            <button onClick={handleCreateStudent}>Create Student</button><br /><br />
            <button onClick={handleCreateCourse}>Create Course</button><br /><br />
            <button onClick={handleCreateStudentEnrollment}>Create Student Enrollment</button><br /><br />
            <button onClick={handleCreateTeacherEnrollment}>Create Teacher Enrollment</button><br />

            {currentAction && (
                <form onSubmit={handleSubmit}>
                    {currentAction === 'createTeacher' && (
                        <>
                            <br />
                            <label>
                                Teacher Username:
                                <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
                            </label>
                            <br /><br />
                            <label>
                                Teacher Password:
                                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                            </label>
                        </>
                    )}
                    {currentAction === 'createStudent' && (
                        <>
                            <br />
                            <label>
                                Student Username:
                                <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
                            </label>
                            <br /><br />
                            <label>
                                Student Password:
                                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                            </label>
                        </>
                    )}
                    {currentAction === 'createCourse' && (
                        <>
                            <br />
                            <label>
                                Course ID:
                                <input type="text" value={id} onChange={(e) => setId(e.target.value)} required />
                            </label>
                            <br /><br />
                            <label>
                                Course Name:
                                <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
                            </label>
                        </>
                    )}
                    {currentAction === 'createStudentEnrollment' && (
                        <>
                            <br />
                            <label>
                                Student ID:
                                <input type="text" value={studentID} onChange={(e) => setStudentID(e.target.value)} required />
                            </label>
                            <br /><br />
                            <label>
                                Course ID:
                                <input type="text" value={id} onChange={(e) => setId(e.target.value)} required />
                            </label>
                        </>
                    )}
                    {currentAction === 'createTeacherEnrollment' && (
                        <>
                            <br />
                            <label>
                                Teacher ID:
                                <input type="text" value={teacherID} onChange={(e) => setTeacherID(e.target.value)} required />
                            </label>
                            <br /><br />
                            <label>
                                Course ID:
                                <input type="text" value={id} onChange={(e) => setId(e.target.value)} required />
                            </label>
                        </>
                    )}
                    <br /><br />
                    <button type="submit">Create</button>
                </form>
            )}
            {isCreated && <h4 style={{ color: 'green' }}>Created Successfully!</h4>}
            <br />
            <button onClick={handleLogout}>Log out</button> <br /><br />
            <button onClick={() => navigate('/main')}>Home</button>
        </div>
    );
};

export default AdminCreation;
