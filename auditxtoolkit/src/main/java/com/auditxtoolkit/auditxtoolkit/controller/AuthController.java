package com.auditxtoolkit.auditxtoolkit.controller;

import com.auditxtoolkit.auditxtoolkit.dto.JwtResponseDTO;
import com.auditxtoolkit.auditxtoolkit.dto.UserRequestDTO;
import com.auditxtoolkit.auditxtoolkit.dto.UserResponseDTO;
import com.auditxtoolkit.auditxtoolkit.security.JwtUtil;
import com.auditxtoolkit.auditxtoolkit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody UserRequestDTO loginRequest) {
        UserResponseDTO user = userService.getUserByEmailAndPassword(
                loginRequest.getEmail(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}
