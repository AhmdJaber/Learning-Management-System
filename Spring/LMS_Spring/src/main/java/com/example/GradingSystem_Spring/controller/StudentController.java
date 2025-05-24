package com.example.GradingSystem_Spring.controller;

import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.model.entities.student.Student;
import com.example.GradingSystem_Spring.security.jwt.JWTService;
import com.example.GradingSystem_Spring.service.StudentEnrollmentService;
import com.example.GradingSystem_Spring.service.StudentService;
import com.example.GradingSystem_Spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5000")
@RestController
@RequestMapping("/student")
public class StudentController {
    public static Boolean cond = false;
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentEnrollmentService studentEnrollmentService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/getAllGrades/{studentID}")
    public ResponseEntity<?> getAllGrades(@PathVariable String studentID) {
        Student student = studentService.getStudentById(Long.parseLong(studentID));
        if (student != null) {
            List<Course> courses = studentEnrollmentService.findCoursesByStudentID(Long.parseLong(studentID));
            if (courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No courses found for this student");
            }
            return ResponseEntity.ok(courses);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    @PostMapping("/login")
    public String login(@RequestBody Student student) {
        AdminController.cond = false;
        TeacherController.cond = false;
        cond = true;

        if (studentService.authenticate(student)){
            return jwtService.getToken(student.getName(), "student");
        }
        return "";
    }
}
