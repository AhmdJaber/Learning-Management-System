package com.example.GradingSystem_Spring.controller;

import com.example.GradingSystem_Spring.model.entities.admin.Admin;
import com.example.GradingSystem_Spring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("http://localhost:5000")
@RestController
@RequestMapping("/super")
public class SuperAdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        Admin created = adminService.addAdmin(admin);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("success", false));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        }
    }
}
