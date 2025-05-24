package org.example.controller;

import org.example.model.Admin;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.services.auth.AuthService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientController {
    public static int pickAction(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("Pick number from the following list: ");
        writer.println("1. Login to Super Admin");
        writer.println("2. Login to Admin");
        writer.println("3. Login to Teacher");
        writer.println("4. Login to Student");
        writer.println("5. Exit");

        int num = Integer.parseInt(reader.readLine());
        if (num >= 1 && num <= 5){
            return num;
        }
        writer.println("Invalid number. Please try again.");
        return pickAction(reader, writer);
    }

    public static void makeAction(BufferedReader reader, PrintWriter writer, Connection conn) throws IOException, SQLException {
        while(true){
            int num = ClientController.pickAction(reader, writer);
            if (num == 1){
                if (AuthService.loginToSuperAdmin(reader, writer)){
                    SuperAdminController.makeAction(reader, writer);
                } else {
                    writer.println("invalid super admin password!");
                }
            }
            else if (num == 2){
                Object obj = AuthService.login(reader, writer, conn, "admin");
                if (obj != null){
                    Admin admin = (Admin) obj;
                    writer.println("Logged in to Admin");
                    writer.println(admin);
                    AdminController.makeAction(reader, writer, conn, admin);
                }
                else {
                    writer.println("Login failed!");
                }
            }
            else if (num == 3){
                Object obj = AuthService.login(reader, writer, conn, "teacher");
                if (obj != null){
                    Teacher teacher = (Teacher) obj;
                    writer.println("Logged in to Teacher");
                    writer.println(teacher);
                    TeacherController.makeAction(reader, writer, teacher);
                }
                else {
                    writer.println("Login failed!");
                }
            }
            else if (num == 4){
                Object obj = AuthService.login(reader, writer, conn, "student");
                if (obj != null){
                    Student student = (Student) obj;
                    writer.println("Logged in to Student");
                    writer.println(student);
                    StudentController.makeAction(reader, writer, student);
                }
                else {
                    writer.println("Login failed!");
                }
            }
            else if (num == 5){
                writer.println("Exited!");
                break;
            }
        }
    }
}
