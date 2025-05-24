package servlet.entities;

import database.MySQLDatabase;
import services.entities.SuperAdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/super")
public class SuperAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Boolean auth = (Boolean) session.getAttribute("superAuth");
        if (auth == null || !auth) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            session.setAttribute("superAuth", false);
            session.setAttribute("authType", "Super Admin");
            session.setAttribute("super", "Super Duper Admin");
            res.sendRedirect("/main/auth");
            return;
        }

        String exit = req.getParameter("exit");
        if (exit != null) {
            if (exit.equals("logout")){
                session.invalidate();
            }
            res.sendRedirect("/main");
            return;
        }

        getServletContext().getRequestDispatcher("/jsp/SuperAdmin/SuperAdmin.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (Objects.equals(action, "create")) {
            try {
                createAdmin(req, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        doGet(req, res);
    }

    public void createAdmin(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        Connection conn = MySQLDatabase.getConnection();
        session.setAttribute("created", false);
        if (SuperAdminService.createAdmin(conn, name, password)) {
            res.setStatus(200);
            session.setAttribute("created", true);
        } else {
            res.setStatus(403);
        }
    }
}
