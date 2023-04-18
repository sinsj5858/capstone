package com.example.capstone.controller;

import com.example.capstone.domain.GetRestaurant;
import com.example.capstone.domain.GetMenu;
import com.example.capstone.domain.Restaurant;
import com.example.capstone.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    //식당등록
    @PostMapping("/RestaurantRegister")
    public ResponseEntity<String> restaurantRegister(@RequestBody GetRestaurant getRestaurant,
                                                     @RequestParam(value = "restaurantImg" ,required = false)MultipartFile restaurantImg,
                                                     @RequestParam(value = "menuImgList", required = false)List<MultipartFile> menuImgList)throws IOException{
//        throws IOException
        if(restaurantService.RestaurantRegister(getRestaurant,restaurantImg,menuImgList)){
            return ResponseEntity.ok().body("success");  //식당등록 완료
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
