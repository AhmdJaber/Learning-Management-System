package org.example.controller;

import org.example.model.Admin;
import org.example.services.entities.AdminService;
import org.example.utils.Creator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class AdminController {
    private static Integer adminActions(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("Pick number from the following list: ");
        writer.println("1. Create Teacher");
        writer.println("2. Create Student");
        writer.println("3. Create Course");
        writer.println("4. Enroll student in course");
        writer.println("5. Associate teacher with course");
        writer.println("6. Exit Admin");

        int num = Integer.parseInt(reader.readLine());
        if (num >= 1 && num <= 6) {
            return num;
        }
        writer.println("Please enter a number between 1 and 6");
        return adminActions(reader, writer);
    }

    public static void makeAction(BufferedReader reader, PrintWriter writer, Connection conn, Admin admin) throws SQLException, IOException {
        while (true) {
            int num = adminActions(reader, writer);
            if (num == 1) {
                Creator.createEntity(reader, writer, conn, "teacher");
            } else if (num == 2) {
                Creator.createEntity(reader, writer, conn, "student");
            } else if (num == 3) {
                Creator.createCourse(reader, writer, conn);
            } else if (num == 4) {
                writer.println("Enter student ID: ");
                String studentID = reader.readLine();
                writer.println("Enter course ID: ");
                String courseID = reader.readLine();
                if (AdminService.createEnrollment(conn, studentID, courseID)){
                    writer.println("Enrollment created by admin: " + admin.getName());
                } else {
                    writer.println("Failed to create enrollment");
                }
            } else if (num == 5) {
                writer.println("Enter teacher ID: ");
                String teacherID = reader.readLine();
                writer.println("Enter course ID: ");
                String courseID = reader.readLine();
                if (AdminService.associateCourse(conn, teacherID, courseID)){
                    writer.println("Association created by admin: " + admin.getName());
                } else {
                    writer.println("Failed to create association");
                }
            } else {
                writer.println("Admin " + admin.getName() + " exited!");
                return;
            }
        }
    }
}
