package com.example.capstone.controller;

import com.example.capstone.domain.GetRestaurant;
import com.example.capstone.domain.User;
import com.example.capstone.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestaurantRegisterController {
    private final RestaurantService restaurantService;
    public RestaurantRegisterController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @PostMapping("/RestaurantRegister")
    public ResponseEntity<String> restaurantRegister(@RequestBody GetRestaurant getRestaurant){

    }
}
