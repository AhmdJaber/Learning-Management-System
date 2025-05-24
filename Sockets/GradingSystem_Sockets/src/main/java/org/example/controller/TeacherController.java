package org.example.controller;

import org.example.model.Course;
import org.example.model.Teacher;
import org.example.services.entities.TeacherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class TeacherController {
    private static Integer teacherAction(BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("Pick number from the following list: ");
        writer.println("1. Get all associated courses");
        writer.println("2. Update student grades for an associated course");
        writer.println("3. Exit");

        int num = Integer.parseInt(reader.readLine());
        if (num >= 1 && num <= 3) {
            return num;
        }
        writer.println("Please enter a number between 1 and 3");
        return teacherAction(reader, writer);
    }

    public static void makeAction(BufferedReader reader, PrintWriter writer, Teacher teacher) throws SQLException, IOException {
        while (true) {
            int num = teacherAction(reader, writer);
            if (num == 1) {
                List<Course> courses = TeacherService.getAssociatedCourses(teacher.getId().toString());
                writer.println(courses.toString());
            } else if (num == 2) {
                writer.println("Enter the course id: ");
                String courseId = reader.readLine();
                TeacherService.updateStudentsGrades(reader, writer, teacher.getId().toString(), courseId);
            } else {
                writer.println("Teacher exited!");
                return;
            }
        }
    }
}
