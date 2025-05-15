package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.model.User;
import com.auditxtoolkit.auditxtoolkit.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndGetUser() {
        User user = new User("testuser", "Test", "User", "he/him", "test@example.com", "Password123!");
        userService.createUser(user);

        List<User> users = userService.getAllUsers();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testGetUserById() {
        User user = new User("testuser2", "Test2", "User2", "she/her", "test2@example.com", "Password123!");
        User saved = userService.createUser(user);

        User found = userService.getUserById(saved.getId());
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("testuser2");
    }

    @Test
    void testUpdateUser() {
        User user = new User("testuser3", "Test3", "User3", "they/them", "test3@example.com", "Password123!");
        User saved = userService.createUser(user);

        saved.setName("UpdatedName");
        userService.updateUser(saved.getId(), saved);

        User updated = userService.getUserById(saved.getId());
        assertThat(updated.getName()).isEqualTo("UpdatedName");
    }

    @Test
    void testDeleteUser() {
        User user = new User("testuser4", "Test4", "User4", "he/him", "test4@example.com", "Password123!");
        User saved = userService.createUser(user);

        userService.deleteUser(saved.getId());
        User deleted = userService.getUserById(saved.getId());
        assertThat(deleted).isNull();
    }
}
