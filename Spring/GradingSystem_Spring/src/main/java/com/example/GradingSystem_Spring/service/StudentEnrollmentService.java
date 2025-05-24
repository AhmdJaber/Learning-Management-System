package com.example.GradingSystem_Spring.service;

import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollment;
import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollmentId;
import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.model.entities.student.Student;
import com.example.GradingSystem_Spring.repository.StudentEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentEnrollmentService {
    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    public List<StudentEnrollment> getAllStudentEnrollments() {
        return studentEnrollmentRepository.findAll();
    }

    public StudentEnrollment getStudentEnrollmentById(StudentEnrollmentId studentEnrollmentId) {
        return studentEnrollmentRepository.findById(studentEnrollmentId).orElseThrow( () ->
                new NoSuchElementException("Student Enrollment with id " + studentEnrollmentId + " not found")
        );
    }

    public StudentEnrollment addStudentEnrollment(StudentEnrollment studentEnrollment) {
        return studentEnrollmentRepository.save(studentEnrollment);
    }

    public StudentEnrollment updateStudentEnrollment(StudentEnrollmentId studentEnrollmentId, Double grade) {
        return studentEnrollmentRepository.findById(studentEnrollmentId).map(studentEnrollment -> {
            studentEnrollment.setGrade(grade);
            return studentEnrollmentRepository.save(studentEnrollment);
        }).orElseThrow(() -> new NoSuchElementException("Student Enrollment with ID " + studentEnrollmentId + " not found"));
    }

    public boolean deleteStudentEnrollment(StudentEnrollmentId studentEnrollmentId) {
        if (studentEnrollmentRepository.existsById(studentEnrollmentId)) {
            studentEnrollmentRepository.deleteById(studentEnrollmentId);
            return true;
        }
        return false;
    }

    public List<Student> findStudentsByCourseID(String courseID){
        return studentEnrollmentRepository.findStudentsByCourseID(courseID);
    }

    public List<Course> findCoursesByStudentID(Long studentID){
        return studentEnrollmentRepository.findCoursesByStudentID(studentID);
    }
}
