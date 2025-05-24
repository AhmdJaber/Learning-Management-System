package com.example.GradingSystem_Spring.controller;

import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollmentId;
import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.model.entities.teacher.Teacher;
import com.example.GradingSystem_Spring.model.entities.student.Student;
import com.example.GradingSystem_Spring.security.jwt.JWTService;
import com.example.GradingSystem_Spring.service.StudentEnrollmentService;
import com.example.GradingSystem_Spring.service.TeacherEnrollmentService;
import com.example.GradingSystem_Spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:5000")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    public static boolean cond = false;
    @Autowired
    private TeacherEnrollmentService teacherEnrollmentService;

    @Autowired
    private StudentEnrollmentService studentEnrollmentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/allCourses/{teacherID}")
    public ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable String teacherID) {
        return ResponseEntity.ok(teacherEnrollmentService.findCoursesByTeacherId(Long.parseLong(teacherID)));
    }

    @GetMapping("/allStudentsOfSomeCourse/{courseID}")
    public ResponseEntity<List<Student>> getStudentsOfSomeCourse(@PathVariable String courseID) {
        return ResponseEntity.ok(studentEnrollmentService.findStudentsByCourseID(courseID));
    }

    @PostMapping("/updateGrades/{courseID}")
    public ResponseEntity<String> updateGrades(@PathVariable String courseID, @RequestBody List<Map<String, Object>> studentsData) {
        try {
            for (Map<String, Object> studentData : studentsData) {
                Long studentID = Long.valueOf(studentData.get("id").toString());
                Double grade = Double.valueOf(studentData.get("grade").toString());

                StudentEnrollmentId studentEnrollmentId = new StudentEnrollmentId(studentID, courseID);
                studentEnrollmentService.updateStudentEnrollment(studentEnrollmentId, grade);
            }
            return ResponseEntity.ok("Grades updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while updating grades: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Teacher teacher) {
        AdminController.cond = false;
        StudentController.cond = false;
        cond = true;

        if (teacherService.authenticate(teacher)){
            return jwtService.getToken(teacher.getName(), "teacher");
        }
        return "";
    }
}