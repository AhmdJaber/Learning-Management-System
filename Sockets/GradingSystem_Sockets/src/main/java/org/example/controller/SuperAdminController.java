package org.example.controller;

import org.example.database.MySQLDatabase;
import org.example.utils.Creator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class SuperAdminController {
    private static Integer superAdminAction(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("Pick number from the following list: ");
        writer.println("1. Create a new Admin");
        writer.println("2. Exit");

        int num = Integer.parseInt(reader.readLine());
        if (num < 1 || num > 2) {
            writer.println("Please enter 1 or 2");
            return superAdminAction(reader, writer);
        }
        return num;
    }

    public static void makeAction(BufferedReader reader, PrintWriter writer) throws SQLException, IOException {
        while (true) {
            int num = superAdminAction(reader, writer);
            Connection conn = MySQLDatabase.getConnection();
            if (num == 1) {
                Creator.createEntity(reader, writer, conn, "admin");
            } else  {
                writer.println("Super Admin Exited!");
                return;
            }
        }
    }
}
