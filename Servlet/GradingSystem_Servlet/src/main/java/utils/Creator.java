package utils;

import services.entities.AdminService;
import services.entities.SuperAdminService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class Creator {
    public static Boolean createEntity(Connection conn, String entity, String name, String pass) throws SQLException {
        if (Objects.equals(entity, "admin")) {
            if (SuperAdminService.createAdmin(conn, name, pass)) {
                System.out.println("Admin with name = [ " + name + " ] and password = [ " + pass + " ] created successfully!");
                return true;
            }
            System.out.println("Failed to create admin");
            return false;

        } else if (Objects.equals(entity, "student")) {
            if (AdminService.createStudent(conn, name, pass)){
                System.out.println("Student with name = [ " + name + " ] and password = [ " + pass + " ] created successfully!");
                return true;
            }
            System.out.println("Failed to create student");
            return false;
        } else if (Objects.equals(entity, "teacher")) {
            if (AdminService.createTeacher(conn, name, pass)) {
                System.out.println("Teacher with name = [ " + name + " ] and password = [ " + pass + " ] created successfully!");
                return true;
            }
            System.out.println("Failed to create teacher");
            return false;
        }
        System.out.println("Invalid entity: " + entity);
        return false;
    }

    public static Boolean createEnrollment(Connection conn, String studentID, String courseID) throws SQLException {
        if (AdminService.createEnrollment(conn, studentID, courseID)){
            System.out.println("Enrollment created!");
            return true;
        }
        System.out.println("Failed to create enrollment");
        return false;
    }

    public static Boolean createAssociation(Connection conn, String teacherID, String courseID) throws SQLException {
        if (AdminService.associateCourse(conn, teacherID, courseID)){
            System.out.println("Association created!");
            return true;
        }
        System.out.println("Failed to create association");
        return false;
    }

    public static Boolean createCourse(Connection conn, String courseID, String courseName) throws SQLException {
        if (AdminService.createCourse(conn, courseID, courseName)){
            System.out.println("Course created!");
            return true;
        }
        System.out.println("Error while creating the course!");
        return false;
    }

}
