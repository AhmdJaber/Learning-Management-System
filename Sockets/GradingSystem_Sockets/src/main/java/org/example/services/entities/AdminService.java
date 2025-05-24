package org.example.services.entities;

import org.example.services.database.DBService;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminService {
    public static boolean createStudent(Connection conn, String name, String password) throws SQLException {
        return DBService.createEntity(conn, "student", name, password);
    }

    public static boolean createTeacher(Connection conn, String name, String password) throws SQLException {
        return DBService.createEntity(conn, "teacher", name, password);
    }

    public static boolean createCourse(Connection conn, String id, String name) throws SQLException {
        return DBService.createCourse(conn, id, name);
    }

    public static boolean createEnrollment(Connection conn, String studentID, String courseID) throws SQLException {
        return DBService.enrollStudentInCourse(conn, studentID, courseID);
    }

    public static boolean associateCourse(Connection conn, String teacherID, String courseID) throws SQLException {
        return DBService.createTeacherCourseAssociation(conn, teacherID, courseID);
    }
}
