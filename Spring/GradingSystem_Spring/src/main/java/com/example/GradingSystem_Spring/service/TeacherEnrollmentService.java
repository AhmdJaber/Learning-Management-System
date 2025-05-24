package com.example.GradingSystem_Spring.service;

import com.example.GradingSystem_Spring.model.enrollments.teacher.TeacherEnrollment;
import com.example.GradingSystem_Spring.model.enrollments.teacher.TeacherEnrollmentId;
import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.repository.TeacherEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherEnrollmentService {
    @Autowired
    private TeacherEnrollmentRepository TeacherEnrollmentRepository;
    @Autowired
    private TeacherEnrollmentRepository teacherEnrollmentRepository;

    public List<TeacherEnrollment> getAllTeacherEnrollments() {
        return TeacherEnrollmentRepository.findAll();
    }

    public TeacherEnrollment getTeacherEnrollmentById(TeacherEnrollmentId TeacherEnrollmentId) {
        return TeacherEnrollmentRepository.findById(TeacherEnrollmentId).orElseThrow( () ->
                new NoSuchElementException("Teacher Enrollment with id " + TeacherEnrollmentId + " not found")
        );
    }

    public TeacherEnrollment addTeacherEnrollment(TeacherEnrollment TeacherEnrollment) {
        return TeacherEnrollmentRepository.save(TeacherEnrollment);
    }

    public boolean deleteTeacherEnrollment(TeacherEnrollmentId TeacherEnrollmentId) {
        if (TeacherEnrollmentRepository.existsById(TeacherEnrollmentId)) {
            TeacherEnrollmentRepository.deleteById(TeacherEnrollmentId);
            return true;
        }
        return false;
    }

    public List<Course> findCoursesByTeacherId(Long teacherId) {
        return teacherEnrollmentRepository.findCoursesByTeacherId(teacherId);
    }
}
