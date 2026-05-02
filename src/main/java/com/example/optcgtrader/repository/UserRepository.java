package com.example.optcgtrader.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.optcgtrader.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}