package servlet.entities;

import database.MySQLDatabase;
import utils.Creator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("exit");
        if (action != null){
            if (action.equals("logout")){
                session.invalidate();
            }
            res.sendRedirect("/main");
            return;
        }

        action = req.getParameter("create");
        if (action != null){
            session.setAttribute("entity", action);
            try {
                createEntity(req, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Boolean auth = (Boolean) session.getAttribute("adminAuth");
        if (auth == null || !auth){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            session.setAttribute("adminAuth", false);
            session.setAttribute("authType", "Admin");
            res.sendRedirect("/main/auth");
            return;
        }

        res.setStatus(200);
        getServletContext().getRequestDispatcher("/jsp/Admin/Admin.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null){
            try {
                createEntity(req, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        doGet(req, res);
    }

    public void createEntity(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
        String first = req.getParameter("first");
        String second = req.getParameter("second");
        HttpSession session = req.getSession();
        boolean created = false;
        if (first != null && second != null){
            String entity = (String) session.getAttribute("entity");
            Connection conn = MySQLDatabase.getConnection();
            if (entity.equals("createTeacher")){
                created = Creator.createEntity(conn, "teacher", first, second);
            } else if (entity.equals("createStudent")){
                created = Creator.createEntity(conn, "student", first, second);
            } else if (entity.equals("createCourse")){
                created = Creator.createCourse(conn, first, second);
            } else if (entity.equals("createEnrollment")){
                created = Creator.createEnrollment(conn, first, second);
            } else if (entity.equals("createAssociation")){
                created = Creator.createAssociation(conn, first, second);
            }
        }

        if (created){
            res.setStatus(HttpServletResponse.SC_CREATED);
            req.getSession().setAttribute("created", true);
        } else {
            res.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}
