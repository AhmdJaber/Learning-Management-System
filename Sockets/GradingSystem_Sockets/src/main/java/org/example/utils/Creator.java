package org.example.utils;

import org.example.services.entities.AdminService;
import org.example.services.entities.SuperAdminService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class Creator {
    public static void createEntity(BufferedReader reader, PrintWriter writer, Connection conn, String entity) throws SQLException, IOException {
        String name = Readers.readUsername(reader, writer, entity);
        String pass = Readers.readPassword(reader, writer, entity);

        if (Objects.equals(entity, "admin")) {
            if (SuperAdminService.createAdmin(conn, name, pass)) {
                writer.println("Admin with name = [ " + name + " ] and password = [ " + pass + " ] created successfully!");
            } else {
                writer.println("Failed to create admin");
            }
        } else if (Objects.equals(entity, "student")) {
            if (AdminService.createStudent(conn, name, pass)){
                writer.println("Student with name = [ " + name + " ] and password = [ " + pass + " ] created successfully!");
            } else {
                writer.println("Failed to create student");
            }
        } else if (Objects.equals(entity, "teacher")) {
            if (AdminService.createTeacher(conn, name, pass)) {
                writer.println("Teacher with name = [ " + name + " ] and password = [ " + pass + " ] created successfully!");
            } else {
                writer.println("Failed to create teacher");
            }
        } else {
            writer.println("Invalid entity: " + entity);
        }
    }

    public static void createCourse(BufferedReader reader, PrintWriter writer, Connection conn) throws SQLException, IOException {
        writer.println("Enter the course ID: ");
        String courseID = reader.readLine();
        writer.println("Enter the course name: ");
        String courseName = reader.readLine();

        if (AdminService.createCourse(conn, courseID, courseName)){
            writer.println("Course created!");
        } else {
            writer.println("Error while creating the course!");
        }
    }
}
