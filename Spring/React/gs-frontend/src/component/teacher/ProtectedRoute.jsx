import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ component: Component }) => {
    const token = localStorage.getItem('teacherToken');

    if (!token) {
        return <Navigate to="/teacher/login" />;
    }

    return <Component />;
};

export default ProtectedRoute;
