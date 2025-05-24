package servlet.entities;

import database.MySQLDatabase;
import model.Course;
import model.Student;
import model.Teacher;
import services.database.DBService;
import services.entities.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    private boolean hideCourses = true;
    private boolean hideStudents = true;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("hideCourses", hideCourses);
        session.setAttribute("hideStudents", hideStudents);
        session.setAttribute("showStudents", false);

        Boolean auth = (Boolean) session.getAttribute("teacherAuth");
        if (auth == null || !auth){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            session.setAttribute("teacherAuth", false);
            session.setAttribute("authType", "Teacher");
            res.sendRedirect("/main/auth");
            return;
        }

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            throw new NullPointerException("Teacher is null");
        }
        String action = req.getParameter("action");
        if (action != null) {
            if (action.equals("getAllCourses")) {
                try {
                    String id = teacher.getId().toString();
                    List<Course> courses = getAllCourses(id);
                    session.setAttribute("courses", courses);
                    getServletContext().getRequestDispatcher("/jsp/Teacher/Teacher.jsp").forward(req, res);
                    hideCourses = !hideCourses;
                    session.setAttribute("hide", hideCourses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String getStudents = req.getParameter("getStudents");
        if (getStudents != null) {
            try {
                String courseID = req.getParameter("courseID");
                session.setAttribute("courseID", courseID);
                List<Student> students = getAllStudents(req, res, courseID);
                session.setAttribute("students", students);
                session.setAttribute("showStudents", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            hideStudents = !hideStudents;
            session.setAttribute("hide", hideStudents);
        }

        String exit = req.getParameter("exit");
        if (exit != null){
            if (exit.equals("logout")){
                session.invalidate();
            }
            res.sendRedirect("/main");
            return;
        }

        getServletContext().getRequestDispatcher("/jsp/Teacher/Teacher.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection conn = MySQLDatabase.getConnection();
        String courseID = session.getAttribute("courseID").toString();
        List<Student> students;
        try {
            students = getAllStudents(req, res, courseID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Student student : students) {
            String paramName = "studentGrade" + student.getId();
            int grade = Integer.parseInt(req.getParameter(paramName));
            try {
                DBService.updateStudentGrade(conn, student.getId().toString(), courseID, grade);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        session.setAttribute("updated", true);
        doGet(req, res);
    }

    public List<Course> getAllCourses(String teacherID) throws SQLException {
        return TeacherService.getAssociatedCourses(teacherID);
    }

    public List<Student> getAllStudents(HttpServletRequest req, HttpServletResponse res, String courseID) throws SQLException {
        Connection conn = MySQLDatabase.getConnection();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String teacherID = teacher.getId().toString();
        if (!DBService.checkTeacherAssociation(conn, teacherID, courseID)) {
            return null;
        }

        List<Student> allStudents = new ArrayList<>();
        ResultSet students = DBService.getStudentEnrollmentsByCourseID(conn, courseID);
        while (students.next()) {
            int studentID = students.getInt("student_id");
            ResultSet result = DBService.getEntities(conn, "student", Integer.toString(studentID));
            result.next();
            String studentName = result.getString("student_name");
            Student student = new Student(studentID, studentName, null);
            allStudents.add(student);
        }

        return allStudents;
    }
}