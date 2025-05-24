import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ component: Component }) => {
    const token = localStorage.getItem('studentToken');

    if (!token) {
        return <Navigate to="/student/login" />;
    }

    return <Component />;
};

export default ProtectedRoute;
