package com.app.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByEmail (String email);
    
    boolean existsByUsername (String username);

    boolean existsByEmailAndIdNot (String email, Long id);

    boolean existsByUsernameAndIdNot (String username, Long id);
}