package com.example.capstone.controller;

import com.example.capstone.domain.GetRestaurant;
import com.example.capstone.domain.GetRestaurantMenu;
import com.example.capstone.domain.Restaurant;
import com.example.capstone.domain.User;
import com.example.capstone.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RestaurantRegisterController {
    private final RestaurantService restaurantService;
    public RestaurantRegisterController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    //식당등록
    @PostMapping("/RestaurantRegister")
    public ResponseEntity<String> restaurantRegister(@RequestBody GetRestaurant getRestaurant)throws IOException{
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(getRestaurant.getRestaurantName());
        restaurant.setRestaurantLocation(getRestaurant.getRestaurantLocation());
        restaurant.setRestaurantOperatingTime(getRestaurant.getRestaurantOperatingTime());
        MultipartFile img = getRestaurant.getRestaurantImg(); //식당 이미지
        List<GetRestaurantMenu> menuList = getRestaurant.getMenuList();
        if(restaurantService.RestaurantRegister(restaurant,img,menuList)){
            return ResponseEntity.ok().body("success");  //식당등록 완료
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
