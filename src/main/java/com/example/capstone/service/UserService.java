package com.example.capstone.service;

import com.example.capstone.domain.User;
import com.example.capstone.domain.UserValidatefDuplicate;
import com.example.capstone.exception.ResourceNotFoundException;
import com.example.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        existingUser.setName(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }


    // 로그인 기능
    public Boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            System.out.println(username);
            System.out.println(password);
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    //회원가입 기능     구현 못함
//    public Long join(User user) { //회원가입
//        if(validatefDuplicateUser(user)) //중복회원검증
//            return
//        userRepository.save(user);
//        return user.getId();
//    }

    public Boolean validatefDuplicateUser(String username) {  ///중복회원검증
        Optional<User> user1 = userRepository.findByName(username);
        if(user1.isPresent()){
            return true;
        } else {
            return false;
        }
    }

}
