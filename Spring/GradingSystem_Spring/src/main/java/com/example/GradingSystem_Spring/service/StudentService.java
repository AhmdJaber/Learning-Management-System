package com.example.GradingSystem_Spring.service;

import com.example.GradingSystem_Spring.model.entities.student.Student;
import com.example.GradingSystem_Spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AuthenticationManager manager;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow( () ->
                new NoSuchElementException("Student with id " + id + " not found")
        );
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setPassword(updatedStudent.getPassword());
            return studentRepository.save(student);
        }).orElseThrow(() -> new NoSuchElementException("Student with ID " + id + " not found"));
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean authenticate(Student student){
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(student.getName(), student.getPassword())
        );

        return auth.isAuthenticated();
    }
}
