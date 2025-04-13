package com.bacobogota.todo.controller;

import com.bacobogota.todo.DTO.LoginDTO;
import com.bacobogota.todo.service.UserService;
import com.bacobogota.todo.security.JwtUtil; // ✅ importa aquí
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        if (userService.authenticate(loginDTO.getEmail(), loginDTO.getPassword())) {
            String token = jwtUtil.generateToken(loginDTO.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
