package com.example.GradingSystem_Spring.service;

import com.example.GradingSystem_Spring.model.entities.admin.Admin;
import com.example.GradingSystem_Spring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager manager;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow( () ->
                new NoSuchElementException("Admin with id " + id + " not found")
        );
    }

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        return adminRepository.findById(id).map(admin -> {
            admin.setName(updatedAdmin.getName());
            admin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new NoSuchElementException("Admin with ID " + id + " not found"));
    }

    public boolean deleteAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean authenticate(Admin admin){
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getName(), admin.getPassword())
        );

        return auth.isAuthenticated();
    }
}
