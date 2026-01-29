package com.levelpro.demo.repository;

import com.levelpro.demo.model.Role;
import com.levelpro.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(Role role);

    User findByEmail(String email);
}
