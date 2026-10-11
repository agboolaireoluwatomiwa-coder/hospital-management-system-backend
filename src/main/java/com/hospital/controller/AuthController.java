package com.hospital.controller;

import com.hospital.repository.UserRepository;
import com.hospital.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public record LoginRequest(String username, String password) {}

    public record LoginResponse(String token, String username, String fullName, String role) {}

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthController(UserRepository users, PasswordEncoder encoder, JwtUtil jwt) {
        this.users = users;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return users.findByUsername(req.username())
                .filter(u -> encoder.matches(req.password(), u.getPassword()))
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(new LoginResponse(
                        jwt.generate(u.getUsername(), u.getRole()), u.getUsername(), u.getFullName(), u.getRole())))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("message", "Invalid username or password")));
    }
}
