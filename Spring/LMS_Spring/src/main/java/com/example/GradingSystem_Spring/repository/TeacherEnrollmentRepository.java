package com.example.GradingSystem_Spring.repository;

import com.example.GradingSystem_Spring.model.enrollments.teacher.TeacherEnrollment;
import com.example.GradingSystem_Spring.model.enrollments.teacher.TeacherEnrollmentId;
import com.example.GradingSystem_Spring.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherEnrollmentRepository extends JpaRepository<TeacherEnrollment, TeacherEnrollmentId> {
    @Query("SELECT c FROM Course c JOIN TeacherEnrollment te ON te.id.courseID = c.id WHERE te.id.teacherID = :teacherID")
    List<Course> findCoursesByTeacherId(@Param("teacherID") Long teacherID);
}
