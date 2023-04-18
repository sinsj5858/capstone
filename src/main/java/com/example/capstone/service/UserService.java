package com.example.capstone.service;

import com.example.capstone.domain.Users;
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
//    public User getUserById(Long userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//    }
//
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User updateUser(Long userId, User user) {
//        User existingUser = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//
//        existingUser.setName(user.getUsername());
//        existingUser.setPassword(user.getPassword());
//        existingUser.setName(user.getName());
//
//        return userRepository.save(existingUser);
//    }

//    public void deleteUser(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//
//        userRepository.delete(user);
//    }


    public boolean login(String username, String password) {
        Optional<Users> user = userRepository.findByUsername(username);
        System.out.println(user.get().getUsername());
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;  //로그인 성공
        } else {
            return false; //로그인 실패
        }
    }

    //회원가입 기능     구현 못함
    public boolean join(Users users) { //회원가입
        if(validateDuplicateUser(users.getUsername())) //중복회원검증
            return false;
        userRepository.save(users);
        return true;
    }

    public Boolean validateDuplicateUser(String username) {  ///중복회원검증
        Optional<Users> user1 = userRepository.findByUsername(username);
        System.out.println(user1.isPresent());
        if(user1.isPresent()){
            return true;  // 중복
        } else {
            return false; // 중복X
        }
    }

}
