import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Main = () => {
    const [isCreated, setIsCreated] = useState(false);
    const [showForm, setShowForm] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const adminData = {
            name: formData.get('name'),
            password: formData.get('password'),
        };

        try {
            const response = await fetch('http://localhost:8080/super/createAdmin', { 
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(adminData),
            });

            if (response.ok) {
                setIsCreated(true);
                setShowForm(false); 
            } else {
                console.error('Error creating admin');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const toggleForm = () => {
        setShowForm((prev) => !prev);
    };

    return (
        <div class="container">
            <h1>Super Admin</h1>
            <h4>Make Action:</h4>
            <br />
            <button onClick={toggleForm}>Create Admin</button>
            {showForm && (
                <form onSubmit={handleSubmit}>
                    <br />
                    <label>
                        Username:
                        <input type="text" name="name" placeholder="Enter Admin Name" required />
                    </label>
                    <br /> <br />
                    <label>
                        Password:
                        <input type="password" name="password" placeholder="Enter Admin Password" required />
                    </label>
                    <br /> <br />
                    <button type="submit">Submit Admin Details</button>
                </form>
            )}
            <br /><br />  
            {isCreated && <h4 style={{ color: 'green' }}>Admin Created</h4>}
            <button onClick={() => navigate('/main')}>Home</button>
            
        </div>
    );
};

export default Main;
