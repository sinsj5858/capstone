package com.example.capstone.domain;

public class LoginRequest {
    private String username;
    private String password;


    // getter, setter, 생성자 등 생략
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}