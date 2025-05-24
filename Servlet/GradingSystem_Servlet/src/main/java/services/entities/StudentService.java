package services.entities;

import database.MySQLDatabase;
import model.Course;
import model.Grade;
import model.Student;
import services.database.DBService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static List<Grade> getStudentGrades(Student student) throws SQLException {
        Connection conn = MySQLDatabase.getConnection();
        List<Grade> grades = new ArrayList<>();

        String studentID = student.getId().toString();
        ResultSet courses = DBService.getStudentEnrollmentsByStudentID(conn, studentID);
        while (courses.next()) {
            String courseID = courses.getString("course_id");
            ResultSet res = DBService.getCourses(conn, courseID);
            res.next();
            String courseName = res.getString("course_name");
            int courseGrade = courses.getInt("grade");
            Course course = new Course(courseID, courseName);
            Grade grade = new Grade(student, course, courseGrade);
            grades.add(grade);
        }

        return grades;
    }

}
