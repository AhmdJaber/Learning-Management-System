package services.entities;

import services.database.DBService;

import java.sql.Connection;
import java.sql.SQLException;

public class SuperAdminService {
    public static boolean createAdmin(Connection conn, String name, String password) throws SQLException {
        return DBService.createEntity(conn, "admin", name, password);
    }
}