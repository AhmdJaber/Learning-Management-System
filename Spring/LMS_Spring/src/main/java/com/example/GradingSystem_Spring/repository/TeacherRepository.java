package com.example.GradingSystem_Spring.repository;

import com.example.GradingSystem_Spring.model.entities.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherByName(String name);
}
