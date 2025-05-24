package com.example.GradingSystem_Spring.repository;

import com.example.GradingSystem_Spring.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
