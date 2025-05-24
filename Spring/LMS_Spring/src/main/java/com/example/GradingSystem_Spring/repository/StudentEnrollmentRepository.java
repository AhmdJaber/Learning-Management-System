package com.example.GradingSystem_Spring.repository;

import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollment;
import com.example.GradingSystem_Spring.model.enrollments.student.StudentEnrollmentId;
import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.model.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, StudentEnrollmentId> {
    @Query("SELECT s FROM Student s JOIN StudentEnrollment se ON se.id.studentID = s.id WHERE se.id.courseID = :courseID")
    List<Student> findStudentsByCourseID(@Param("courseID") String courseID);

    @Query("SELECT c FROM Course c JOIN StudentEnrollment se ON se.id.courseID = c.id WHERE se.id.studentID = :studentID")
    List<Course> findCoursesByStudentID(@Param("studentID") Long studentID);
}
