package com.example.GradingSystem_Spring.service;

import com.example.GradingSystem_Spring.model.entities.teacher.Teacher;
import com.example.GradingSystem_Spring.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AuthenticationManager manager;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow( () ->
                new NoSuchElementException("Teacher with id " + id + " not found")
        );
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setName(updatedTeacher.getName());
            teacher.setPassword(updatedTeacher.getPassword());
            return teacherRepository.save(teacher);
        }).orElseThrow(() -> new NoSuchElementException("Teacher with ID " + id + " not found"));
    }

    public Boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean authenticate(Teacher teacher){
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(teacher.getName(), teacher.getPassword())
        );

        return auth.isAuthenticated();
    }
}
