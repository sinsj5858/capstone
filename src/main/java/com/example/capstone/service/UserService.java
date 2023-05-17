package com.example.capstone.service;

import com.example.capstone.domain.User;
import com.example.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;  //로그인 성공
        } else {
            return false; //로그인 실패
        }
    }

    //회원가입 기능     구현 못함
    public boolean join(User user) { //회원가입
        if(validateDuplicateUser(user.getUsername())) //중복회원검증
            return false;
        userRepository.save(user);
        return true;
    }

    public Boolean validateDuplicateUser(String username) {  ///중복회원검증
        Optional<User> user1 = userRepository.findByUsername(username);
        if(user1.isPresent()){
            System.out.println("중복");
            return true;  // 중복
        } else {
            return false; // 중복X
        }
    }

}
