package org.example.services.entities;

import org.example.database.MySQLDatabase;
import org.example.model.Course;
import org.example.services.database.DBService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherService {
    public static List<Course> getAssociatedCourses(String teacherID) throws SQLException {
        List<Course> allCourses = new ArrayList<>();
        Connection conn = MySQLDatabase.getConnection();
        ResultSet courses = DBService.getTeacherAssociations(conn, teacherID);
        while (courses.next()) {
            String courseID = courses.getString("course_id");
            ResultSet res = DBService.getCourses(conn, courseID);
            res.next();
            String courseName = res.getString("course_name");
            Course course = new Course(courseID, courseName);
            allCourses.add(course);
        }

        return allCourses;
    }

    public static void updateStudentsGrades(BufferedReader reader, PrintWriter writer, String teacherID, String courseID) throws SQLException, IOException {
        Connection conn = MySQLDatabase.getConnection();

        if (DBService.checkTeacherAssociation(conn, teacherID, courseID)){
            ResultSet students = DBService.getStudentEnrollmentsByCourseID(conn, courseID);
            while (students.next()) {
                String studentID = students.getString("student_id");
                ResultSet student = DBService.getEntities(conn, "student", studentID);
                student.next();
                String studentName = student.getString("student_name");
                writer.println("Enter the grade for the student: " + studentName);
                int grade = Integer.parseInt(reader.readLine());

                if (DBService.updateStudentGrade(conn, studentID, courseID, grade)){
                    writer.println("Successfully updated!");
                }
                else {
                    writer.println("Failed to update student grade!");
                }
            }
        } else {
            writer.println("The course " + courseID + " doesn't associated with this teacher or doesn't exist!");
        }
    }
}
