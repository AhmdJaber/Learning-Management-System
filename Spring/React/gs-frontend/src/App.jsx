import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import MainPage from './component/main/Main'
import React from 'react';

import AdminCreation from './component/admin/MainAdmin';
import AdminLogin from './component/admin/LoginAdmin';
import ProtectAdmin from './component/admin/ProtectedRoute';

import StudentActions from './component/student/MainStudent';
import StudentLogin from './component/student/LoginStudent';
import ProtectStudent from './component/student/ProtectedRoute';

import TeacherActions from './component/teacher/MainTeacher';
import TeacherLogin from './component/teacher/LoginTeacher';
import ProtectTeacher from './component/teacher/ProtectedRoute';




function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          
          <Route path='/main' element = {<MainPage/>}></Route>
          
          <Route path="/admin/login" element={<AdminLogin />} />
          <Route
              path="/admin"
              element={<ProtectAdmin component={AdminCreation} />}
          />
          <Route path="/student/login" element={<StudentLogin />} />
          <Route
              path="/student"
              element={<ProtectStudent component={StudentActions} />}
          />

          <Route path="/teacher/login" element={<TeacherLogin />} />
          <Route
              path="/teacher"
              element={<ProtectTeacher component={TeacherActions} />}
          />

        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
