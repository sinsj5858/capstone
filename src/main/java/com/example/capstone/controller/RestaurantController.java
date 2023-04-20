package com.example.capstone.controller;

import com.example.capstone.domain.RestaurantRequest;
import com.example.capstone.service.RestaurantService;
import com.google.gson.Gson;
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
    @PostMapping("/RestaurantRegister")   //클라이언트에서 받아올 변수 GetRestaurant,restaurantImg,menuImgList
    public ResponseEntity<String> restaurantRegister(
                                                    @RequestParam(value = "restaurant") String restaurantRequestBody,
//                                                       @RequestParam(value = "restaurant") RestaurantRequest restaurantRequest,
                                                     @RequestParam(value = "restaurantImg" ,required = false)MultipartFile restaurantImg,
                                                     @RequestParam(value = "menuImgList", required = false)List<MultipartFile> menuImgList)throws IOException{
//        throws IOException
        Gson gson = new Gson();
        RestaurantRequest restaurantRequest = gson.fromJson(restaurantRequestBody, RestaurantRequest.class);
        if(restaurantService.RestaurantRegister(restaurantRequest,restaurantImg,menuImgList)){
            return ResponseEntity.ok().body("success");  //식당등록 완료
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
