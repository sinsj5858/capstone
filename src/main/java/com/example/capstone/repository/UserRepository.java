package com.example.capstone.repository;

import com.example.capstone.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User member);
    Optional<User> findById(Long Id);
    Optional<User> findByUsername(String username);
    Optional<User> findByPassword(String password);
    Optional<User> findByName(String name);
    List<User> findAll();
}
