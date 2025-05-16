package com.auditxtoolkit.auditxtoolkit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-/]+$", message = "Only letters, numbers, underscores, hyphens, dots, and slashes are allowed")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // <-- Aquí
    private List<NmapReport> reports = new ArrayList<>();

    // Puedes agregar constructores personalizados si lo necesitas
    public User(String username, String name, String surname, String pronouns, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.pronouns = pronouns;
        this.email = email;
        this.password = password;
    }
}