package services.entities;

import database.MySQLDatabase;
import model.Course;
import services.database.DBService;

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
}
