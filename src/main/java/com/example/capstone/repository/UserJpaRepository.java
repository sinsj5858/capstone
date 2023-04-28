package com.example.capstone.repository;

import com.example.capstone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> ,UserRepository {
    @Override
    Optional<User> findByName(String name); // 사용자 이름으로 검색
    @Override
    Optional<User> findByUsername(String username);

}
