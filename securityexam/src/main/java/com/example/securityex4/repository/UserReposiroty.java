package com.example.securityex4.repository;

import com.example.securityex4.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiroty extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
