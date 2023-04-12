package com.example.capstone.controller;

import com.example.capstone.domain.User;
import com.example.capstone.domain.UserValidateDuplicate;
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
    @PostMapping("/validateDuplicate")
    public ResponseEntity<String> ValidateDuplicate(@RequestBody UserValidateDuplicate userValidateDuplicate) {
        String username = userValidateDuplicate.getUsername();

        boolean result = userService.validateDuplicateUser(username);
        if (result == false) {    // true시 username 이 있다는 소리로 중복됨을 처리해야함
            return ResponseEntity.ok("DuplicateCheck");   // 중복X
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 중복
        }
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean success = userService.join(user);
        if (success) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
