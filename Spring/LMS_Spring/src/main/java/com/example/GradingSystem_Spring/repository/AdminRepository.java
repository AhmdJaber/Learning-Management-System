package com.example.GradingSystem_Spring.repository;

import com.example.GradingSystem_Spring.model.entities.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByName(String name);
}
