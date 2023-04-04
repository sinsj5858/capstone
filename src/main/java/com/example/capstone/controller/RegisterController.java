package com.example.capstone.controller;

import com.example.capstone.domain.User;
import com.example.capstone.domain.UserValidatefDuplicate;
import com.example.capstone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //중복확인     구현 중
    @PostMapping("/validatefDuplicate")
    public ResponseEntity<String> ValidatefDuplicate(@RequestBody UserValidatefDuplicate userValidatefDuplicate) {
        String username = userValidatefDuplicate.getUsername();

        boolean result = userService.validatefDuplicateUser(username);
        if (result) {    // true시 username 이 있다는 소리로 중복됨을 처리해야함
            return ResponseEntity.ok("success");   // 중복
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 중복X
        }
    }


    //회원가입    구현 못함
//    @PostMapping("/register")
//    public ResponseEntity<String> join(@RequestBody User user) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        String name = user.getName();
//
//    }
}
