package com.auditxtoolkit.auditxtoolkit.controller;

import com.auditxtoolkit.auditxtoolkit.dto.request.UserRequestDTO;
import com.auditxtoolkit.auditxtoolkit.dto.response.UserResponseDTO;
import com.auditxtoolkit.auditxtoolkit.service.UserService;
import com.auditxtoolkit.auditxtoolkit.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
// @CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest) {
        UserResponseDTO savedUser = userService.createUser(userRequest);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponseDTO> getUserByEmailAndPassword(@RequestParam String email,
            @RequestParam String password) {
        UserResponseDTO user = userService.getUserByEmailAndPassword(email, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}