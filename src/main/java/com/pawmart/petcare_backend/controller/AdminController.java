package com.pawmart.petcare_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        // Securely check admin credentials (you can later move this to MySQL)
        if ("admin".equals(username) && "pawmart123".equals(password)) {
            return ResponseEntity.ok().body(Map.of("message", "Login Successful"));
        } else {
            return ResponseEntity.status(401).body("Unauthorized Admin");
        }
    }
}