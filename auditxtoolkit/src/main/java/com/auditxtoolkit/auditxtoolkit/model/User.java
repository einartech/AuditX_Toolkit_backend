package com.auditxtoolkit.auditxtoolkit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Integer id;

    @Column
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must have min 3 and max 20 characters")
    @Pattern(regexp = "[a-zA-Z0-9_.-]+$", message = "Only letters and numbers are allowed")
    private String username;

    @Column
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Username must have min 3 and max 20 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters are allowed")
    private String name;

    @Column
    @NotBlank(message = "Surname is required")
    @Size(min = 3, max = 20, message = "Surname must have min 3 and max 20 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters are allowed")
    private String surname;

    @Column
    @NotBlank(message = "Pronouns is required")
    @Size(min = 1, max = 10, message = "Pronouns must have min 1 and max 10 characters")
    @Pattern(regexp = "[a-zA-Z0-9_.-]+$", message = "Only letters and numbers are allowed")
    private String pronouns;

    @Column
    @NotBlank(message = "Email address is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Must have email address format")
    private String email;

    @Column
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    // Constructors
    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPronouns() {
        return this.pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}