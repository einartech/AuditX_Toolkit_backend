package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.repository.UserRepository;
import com.auditxtoolkit.auditxtoolkit.model.User;
import com.auditxtoolkit.auditxtoolkit.dto.request.UserRequestDTO;
import com.auditxtoolkit.auditxtoolkit.dto.response.UserResponseDTO;
import com.auditxtoolkit.auditxtoolkit.exception.UserExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserExceptions.UserNotFoundException(id));
        return toResponseDTO(user);
    }

    public UserResponseDTO getUserByEmailAndPassword(String email, String password) {
        User userEmail = userRepository.findByEmail(email);
        if (userEmail == null || !userEmail.getPassword().equals(password)) {
            throw new UserExceptions.UserNotFoundException("User not found with provided email and password");
        }
        return toResponseDTO(userEmail);
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new UserExceptions.EmailAlreadyExistsException(dto.getEmail());
        }
        User user = toEntity(dto);
        User saved = userRepository.save(user);
        return toResponseDTO(saved);
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO dto) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(dto.getUsername());
            existingUser.setName(dto.getName());
            existingUser.setSurname(dto.getSurname());
            existingUser.setPronouns(dto.getPronouns());
            existingUser.setEmail(dto.getEmail());
            existingUser.setPassword(dto.getPassword());
            User updated = userRepository.save(existingUser);
            return toResponseDTO(updated);
        }).orElseThrow(() -> new UserExceptions.UserNotFoundException(id));
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserExceptions.UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    // Mapper methods
    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPronouns(user.getPronouns());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPronouns(dto.getPronouns());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}