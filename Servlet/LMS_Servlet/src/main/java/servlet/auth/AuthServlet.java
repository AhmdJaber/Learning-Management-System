package servlet.auth;

import database.MySQLDatabase;
import services.auth.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String authType = session.getAttribute("authType").toString();
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        System.out.println();

        String authName = "superAuth";
        if (pass != null){
            if (authType.equals("Super Admin")) {
                if (AuthService.loginToSuperAdmin(pass)) {
                    session.setAttribute(authName, true);
                    req.removeAttribute("error");
                    res.sendRedirect("/main/super");
                    return;
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    session.setAttribute("error", "Invalid Super Admin Password");
                }
            }

            if (name != null){
                try {
                    authName = authType.toLowerCase()+"Auth";
                    if (checkAuth(req, authType, name, pass)){
                        session.setAttribute(authName, true);
                        req.removeAttribute("error");
                        res.sendRedirect("/main/" + authType.toLowerCase());
                        return;
                    } else {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        session.setAttribute("error", "Invalid Username or Password");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        getServletContext().getRequestDispatcher("/jsp/Auth/auth.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {

    }

    public boolean checkAuth(HttpServletRequest req, String table, String name, String pass) throws SQLException {
        Connection conn = MySQLDatabase.getConnection();
        Object entity = AuthService.login(conn, table.toLowerCase(), name, pass);
        System.out.println(entity);
        System.out.println(name + " " + pass);
        HttpSession session = req.getSession();
        System.out.println(entity);
        session.setAttribute(table.toLowerCase(), entity);
        return entity != null;
    }
}
