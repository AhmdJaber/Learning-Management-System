import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function StudentLogin() {
    const [name, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/student/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ name, password }),
            });

            if (response.ok) {
                const token = await response.text();
                localStorage.setItem('studentToken', token); 
                navigate('/student');  
            } else {
                console.error('Login failed');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div class="container">
            <h2>Student Login</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="username"
                        required
                    />
                </label>
                <br /> <br />
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="password"
                        required
                    />
                </label>
                <br /> <br />
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default StudentLogin;
