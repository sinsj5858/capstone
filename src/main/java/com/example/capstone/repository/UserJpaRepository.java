package com.example.capstone.repository;

import com.example.capstone.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<Users, Long> ,UserRepository {
    @Override
    Optional<Users> findByName(String name); // 사용자 이름으로 검색
    @Override
    Optional<Users> findByUsername(String username);

}
