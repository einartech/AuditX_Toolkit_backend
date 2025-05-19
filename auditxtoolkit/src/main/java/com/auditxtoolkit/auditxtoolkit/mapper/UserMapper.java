package com.auditxtoolkit.auditxtoolkit.mapper;

import com.auditxtoolkit.auditxtoolkit.model.User;
import com.auditxtoolkit.auditxtoolkit.dto.request.UserRequestDTO;
import com.auditxtoolkit.auditxtoolkit.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPronouns(dto.getPronouns());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }
}
