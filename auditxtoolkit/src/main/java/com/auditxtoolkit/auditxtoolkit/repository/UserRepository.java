package com.auditxtoolkit.auditxtoolkit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.auditxtoolkit.auditxtoolkit.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}