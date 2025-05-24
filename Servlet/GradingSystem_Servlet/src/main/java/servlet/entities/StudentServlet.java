package servlet.entities;

import model.Grade;
import model.Student;
import services.entities.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private boolean hide = true;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("hide", false);

        Boolean auth = (Boolean) session.getAttribute("studentAuth");
        if (auth == null || !auth){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            session.setAttribute("studentAuth", false);
            session.setAttribute("authType", "Student");
            res.sendRedirect("/main/auth");
            return;
        }

        String action = req.getParameter("action");
        if (action != null) {
            Student student = (Student) session.getAttribute("student");
            try {
                List<Grade> grades = StudentService.getStudentGrades(student);
                session.setAttribute("grades", grades);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            hide = !hide;
            session.setAttribute("hide", hide);
        }

        String exit = req.getParameter("exit");
        if (exit != null) {
            if (exit.equals("logout")){
                session.invalidate();
            }
            res.sendRedirect("/main");
            return;
        }

        getServletContext().getRequestDispatcher("/jsp/Student/Student.jsp").forward(req, res);
    }
}
