package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.dto.request.UserRequestDTO;
import com.auditxtoolkit.auditxtoolkit.dto.response.UserResponseDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(UserService.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserRequestDTO buildUserDTO(String username, String name, String surname, String pronouns, String email,
            String password) {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUsername(username);
        dto.setName(name);
        dto.setSurname(surname);
        dto.setPronouns(pronouns);
        dto.setEmail(email);
        dto.setPassword(password);
        return dto;
    }

    @Test
    void testCreateAndGetUser() {
        UserRequestDTO dto = buildUserDTO("jdoe", "John", "Doe", "he/him", "john.doe@example.com", "Password123!");
        userService.createUser(dto);

        List<UserResponseDTO> users = userService.getAllUsers();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testGetUserById() {
        UserRequestDTO dto = buildUserDTO("asmith", "Alice", "Smith", "she/her", "alice.smith@example.com",
                "Password123!");
        UserResponseDTO saved = userService.createUser(dto);

        UserResponseDTO found = userService.getUserById(saved.getId());
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("asmith");
    }

    @Test
    void testUpdateUser() {
        UserRequestDTO dto = buildUserDTO("bwayne", "Bruce", "Wayne", "he/him", "bruce.wayne@example.com",
                "Password123!");
        UserResponseDTO saved = userService.createUser(dto);

        UserRequestDTO updateDto = buildUserDTO("bwayne", "Batman", "Wayne", "he/him", "bruce.wayne@example.com",
                "Password123!");
        UserResponseDTO updated = userService.updateUser(saved.getId(), updateDto);

        assertThat(updated.getName()).isEqualTo("Batman");
    }

    @Test
    void testDeleteUser() {
        UserRequestDTO dto = buildUserDTO("ckent", "Clark", "Kent", "he/him", "clark.kent@example.com", "Password123!");
        UserResponseDTO saved = userService.createUser(dto);

        userService.deleteUser(saved.getId());
        try {
            userService.getUserById(saved.getId());
            assertThat(true).isFalse();
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(com.auditxtoolkit.auditxtoolkit.exception.UserExceptions.UserNotFoundException.class);
        }
    }
}
