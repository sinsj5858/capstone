package com.example.capstone.repository;

import com.example.capstone.domain.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Users save(Users member);
    Optional<Users> findById(Long Id);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByPassword(String password);
    Optional<Users> findByName(String name);
    List<Users> findAll();
}
