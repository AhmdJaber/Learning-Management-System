package services.database;

import model.Admin;
import model.Student;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DBService {
    public static boolean createEntity(Connection conn, String table,
                                       String name, String password) throws SQLException {
        String nameAttribute = table + "_name";
        String passwordAttribute = table + "_password";
        String query = "INSERT INTO " + table + " (" + nameAttribute + ", " + passwordAttribute + ") VALUES(?, ?)";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, name);
        statement.setString(2, password);

        int rs = statement.executeUpdate();
        return rs > 0;
    }

    public static ResultSet getEntities(Connection conn, String table, String entityID) throws SQLException {
        String idAttribute = table + "_id";
        String query = "SELECT * FROM " + table + " WHERE " + idAttribute + "=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, entityID);

        return statement.executeQuery();
    }

    public static Object checkEntitiesExistence(Connection conn, String table,
                                                String name, String password) throws SQLException {
        String nameAttribute = table + "_name";
        String passwordAttribute = table + "_password";
        String query = "SELECT * FROM " + table + " WHERE " + nameAttribute + "=? AND " + passwordAttribute + "=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, name);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            int entityID = rs.getInt(1);
            String entityName = rs.getString(2);
            String entityPassword = rs.getString(3);
            if (Objects.equals(table, "admin")){
                return new Admin(entityID, entityName, entityPassword);
            } else if (Objects.equals(table, "teacher")){
                return new Teacher(entityID, entityName, entityPassword);
            } else if (Objects.equals(table, "student")){
                return new Student(entityID, entityName, entityPassword);
            }
        }
        return null;
    }

    public static boolean createCourse(Connection conn, String code, String name) throws SQLException {
        String query = "INSERT INTO course (course_id, course_name) VALUES(?, ?)";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, code);
        statement.setString(2, name);

        int rs = statement.executeUpdate();
        return rs > 0;
    }

    public static ResultSet getCourses(Connection conn, String courseId) throws SQLException {
        String query = "SELECT * FROM course WHERE course_id=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, courseId);

        return statement.executeQuery();
    }

    public static boolean enrollStudentInCourse(Connection conn, String studentID, String courseID) throws SQLException{
        String query = "INSERT INTO grades (student_id, course_id) VALUES(?, ?)";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, studentID);
        statement.setString(2, courseID);

        int rs = statement.executeUpdate();
        return rs > 0;
    }

    public static ResultSet getStudentEnrollmentsByStudentID(Connection conn, String studentID) throws SQLException {
        String query = "SELECT * FROM grades WHERE student_id=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, studentID);

        return statement.executeQuery();
    }

    public static ResultSet getStudentEnrollmentsByCourseID(Connection conn, String courseID) throws SQLException {
        String query = "SELECT * FROM grades WHERE course_id=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, courseID);

        return statement.executeQuery();
    }

    public static boolean updateStudentGrade(Connection conn, String studentID, String courseID, int grade) throws SQLException {
        String query = "UPDATE grades SET grade=? WHERE student_id=? AND course_id=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setInt(1, grade);
        statement.setString(2, studentID);
        statement.setString(3, courseID);

        int rs = statement.executeUpdate();
        return rs > 0;
    }

    public static boolean createTeacherCourseAssociation(Connection conn, String teacherID, String courseID) throws SQLException {
        String query = "INSERT INTO teacher_courses (teacher_id, course_id) VALUES(?, ?)";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, teacherID);
        statement.setString(2, courseID);

        int rs = statement.executeUpdate();
        return rs > 0;
    }

    public static ResultSet getTeacherAssociations(Connection conn, String teacherID) throws SQLException {
        String query = "SELECT * FROM teacher_courses WHERE teacher_id=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, teacherID);
        return statement.executeQuery();
    }

    public static boolean checkTeacherAssociation(Connection conn, String teacherID, String courseID) throws SQLException{
        String query = "SELECT * FROM teacher_courses WHERE teacher_id=? AND course_id=?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, teacherID);
        statement.setString(2, courseID);

        ResultSet rs = statement.executeQuery();
        rs.next();

        return rs.getInt(1) > 0;
    }
}
