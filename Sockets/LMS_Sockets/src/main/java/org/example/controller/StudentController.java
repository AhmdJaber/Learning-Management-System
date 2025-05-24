package org.example.controller;

import org.example.model.Grade;
import org.example.model.Student;
import org.example.services.entities.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class StudentController {
    private static Integer studentAction(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("Pick number from the following list: ");
        writer.println("1. Get all grades for enrolled courses");
        writer.println("2. Exit");

        int num = Integer.parseInt(reader.readLine());
        if (num >= 1 && num <= 2) {
            return num;
        }
        writer.println("Please enter 1 or 2");
        return studentAction(reader, writer);
    }

    public static void makeAction(BufferedReader reader, PrintWriter writer, Student student) throws SQLException, IOException {
        while (true) {
            int num = studentAction(reader, writer);
            if (num == 1) {
                List<Grade> grades = StudentService.getStudentGrades(student);
                writer.println("Your grades in all enrolled courses: ");
                writer.println(grades.toString());
            } else  {
                writer.println("Student Exited!");
                return;
            }
        }
    }
}
