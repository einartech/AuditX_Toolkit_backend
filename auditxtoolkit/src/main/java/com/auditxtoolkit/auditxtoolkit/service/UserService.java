package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.repository.UserRepository;
import com.auditxtoolkit.auditxtoolkit.model.User;
import com.auditxtoolkit.auditxtoolkit.exception.UserExceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserExceptions.UserNotFoundException(id));
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User userEmail = userRepository.findByEmail(email);
        if (userEmail == null || !userEmail.getPassword().equals(password)) {
            throw new UserExceptions.UserNotFoundException("User not found with provided email and password");
        }
        return userEmail;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserExceptions.EmailAlreadyExistsException(user.getEmail());
        }
        // Puedes agregar más validaciones aquí si lo deseas
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(user.getUsername());
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setPronouns(user.getPronouns());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new UserExceptions.UserNotFoundException(id));
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserExceptions.UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}