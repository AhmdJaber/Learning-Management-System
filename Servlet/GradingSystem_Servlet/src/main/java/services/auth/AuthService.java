package services.auth;

import services.database.DBService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class AuthService {
    public static Object checkAdminExistence(Connection conn, String name, String password) throws SQLException {
        return DBService.checkEntitiesExistence(conn, "admin", name, password);
    }

    public static Object checkTeacherExistence(Connection conn, String name, String password) throws SQLException{
        return DBService.checkEntitiesExistence(conn, "teacher", name, password);
    }

    public static Object checkStudentExistence(Connection conn, String name, String password) throws SQLException{
        return DBService.checkEntitiesExistence(conn, "student", name, password);
    }

    public static Object login(Connection conn, String entity, String name, String password) throws SQLException {
        if (Objects.equals(entity, "admin")) {
            return AuthService.checkAdminExistence(conn, name, password);
        } else if (Objects.equals(entity, "teacher")) {
            return AuthService.checkTeacherExistence(conn, name, password);
        } else if (Objects.equals(entity, "student")) {
            return AuthService.checkStudentExistence(conn, name, password);
        }
        return null;
    }

    public static boolean loginToSuperAdmin(String password) {
        return Objects.equals(password, "1234578");
    }
}
