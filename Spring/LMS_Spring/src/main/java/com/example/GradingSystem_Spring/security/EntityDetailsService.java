package com.example.GradingSystem_Spring.security;

import com.example.GradingSystem_Spring.controller.AdminController;
import com.example.GradingSystem_Spring.controller.StudentController;
import com.example.GradingSystem_Spring.controller.TeacherController;
import com.example.GradingSystem_Spring.model.entities.admin.Admin;
import com.example.GradingSystem_Spring.model.entities.admin.AdminDetails;
import com.example.GradingSystem_Spring.model.entities.student.Student;
import com.example.GradingSystem_Spring.model.entities.student.StudentDetails;
import com.example.GradingSystem_Spring.model.entities.teacher.Teacher;
import com.example.GradingSystem_Spring.model.entities.teacher.TeacherDetails;
import com.example.GradingSystem_Spring.repository.AdminRepository;
import com.example.GradingSystem_Spring.repository.StudentRepository;
import com.example.GradingSystem_Spring.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EntityDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StudentController.cond){
            Student student = studentRepository.findStudentByName(username);
            if (student == null) {
                System.out.println("Student not found");
                throw new UsernameNotFoundException(username);
            }
            System.out.println(student);
            return new StudentDetails(student);
        } else if (AdminController.cond){
            Admin admin = adminRepository.findAdminByName(username);
            if (admin == null) {
                System.out.println("Admin not found");
                throw new UsernameNotFoundException(username);
            }
            System.out.println(admin);
            return new AdminDetails(admin);
        } else if (TeacherController.cond) {
            Teacher teacher = teacherRepository.findTeacherByName(username);
            if (teacher == null) {
                System.out.println("Teacher not found");
                throw new UsernameNotFoundException(username);
            }
            return new TeacherDetails(teacher);
        } else {
            return null;
        }
    }
}