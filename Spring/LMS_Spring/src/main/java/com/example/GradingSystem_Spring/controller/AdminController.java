package com.example.GradingSystem_Spring.controller;

import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollment;
import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollmentId;
import com.example.GradingSystem_Spring.model.enrollments.teacher.TeacherEnrollment;
import com.example.GradingSystem_Spring.model.enrollments.teacher.TeacherEnrollmentId;
import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.model.entities.student.Student;
import com.example.GradingSystem_Spring.model.entities.teacher.Teacher;
import com.example.GradingSystem_Spring.model.entities.admin.Admin;
import com.example.GradingSystem_Spring.security.jwt.JWTService;
import com.example.GradingSystem_Spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5000")
@RestController
@RequestMapping("/admin")
public class AdminController {
    public static Boolean cond = false;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherEnrollmentService teacherEnrollmentService;
    @Autowired
    private StudentEnrollmentService studentEnrollmentService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/createTeacher")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.addTeacher(teacher);
        if (newTeacher != null) {
            return ResponseEntity.ok(newTeacher);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("/createStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        if (newStudent != null) {
            return ResponseEntity.ok(newStudent);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("/createCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        if (newCourse != null) {
            return ResponseEntity.ok(newCourse);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("/createTeacherEnrollment")
    public ResponseEntity<TeacherEnrollment> addTeacherEnrollment(@RequestBody TeacherEnrollmentId teacherEnrollmentId) {
        TeacherEnrollment teacherEnrollment = teacherEnrollmentService.addTeacherEnrollment(new TeacherEnrollment(teacherEnrollmentId));
        if (teacherEnrollment != null){
            return ResponseEntity.ok(teacherEnrollment);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("/createStudentEnrollment")
    public ResponseEntity<StudentEnrollment> addStudentEnrollment(@RequestBody StudentEnrollmentId studentEnrollmentId) {
        if (courseService.getCourseById(studentEnrollmentId.getCourseID()) != null){
            StudentEnrollment studentEnrollment = studentEnrollmentService.addStudentEnrollment(new StudentEnrollment(studentEnrollmentId));
            if (studentEnrollment != null){
                return ResponseEntity.ok(studentEnrollment);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        StudentController.cond = false;
        TeacherController.cond = false;
        cond = true;

        if (adminService.authenticate(admin)){
            return jwtService.getToken(admin.getName(), "admin");
        }
        return "";
    }
}
