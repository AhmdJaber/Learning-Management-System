package com.example.GradingSystem_Spring.repository;

import com.example.GradingSystem_Spring.model.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByName(String name);
}
