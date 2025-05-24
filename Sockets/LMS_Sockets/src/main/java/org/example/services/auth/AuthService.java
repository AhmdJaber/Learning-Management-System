package org.example.services.auth;

import org.example.services.database.DBService;
import org.example.utils.Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class AuthService {
    public static Object checkAdminExistence(PrintWriter writer, Connection conn, String name, String password) throws SQLException {
        return DBService.checkEntitiesExistence(writer, conn, "admin", name, password);
    }

    public static Object checkTeacherExistence(PrintWriter writer, Connection conn, String name, String password) throws SQLException{
        return DBService.checkEntitiesExistence(writer, conn, "teacher", name, password);
    }

    public static Object checkStudentExistence(PrintWriter writer, Connection conn, String name, String password) throws SQLException{
        return DBService.checkEntitiesExistence(writer, conn, "student", name, password);
    }

    public static Object login(BufferedReader reader, PrintWriter writer, Connection conn, String entity) throws SQLException, IOException {
        String name = Readers.readUsername(reader, writer, entity);
        String password = Readers.readPassword(reader, writer, entity);
        if (Objects.equals(entity, "admin")) {
            return AuthService.checkAdminExistence(writer, conn, name, password);
        } else if (Objects.equals(entity, "teacher")) {
            return AuthService.checkTeacherExistence(writer, conn, name, password);
        } else if (Objects.equals(entity, "student")) {
            return AuthService.checkStudentExistence(writer, conn, name, password);
        }
        writer.println("Invalid entity name!");
        return null;
    }

    public static boolean loginToSuperAdmin(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("Enter super admin password: ");
        String password = reader.readLine();
        return Objects.equals(password, "1234578");
    }
}
