package com.example.capstone.controller;

import com.example.capstone.domain.GetRestaurant;
import com.example.capstone.service.RestaurantService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    //식당등록
    @PostMapping("/RestaurantRegister")
    public ResponseEntity<String> restaurantRegister(
                                                    @RequestParam(value = "restaurant") String restaurantRequestBody,
////                                                       @RequestParam(value = "restaurant") RestaurantRequest restaurantRequest,
                                                     @RequestParam(value = "restaurantImg" ,required = false)MultipartFile restaurantImg,
                                                     @RequestParam(value = "menuImgList", required = false)List<MultipartFile> menuImgList)throws IOException{
        Gson gson = new Gson();
        GetRestaurant getRestaurant = gson.fromJson(restaurantRequestBody, GetRestaurant.class);
        if(restaurantService.RestaurantRegister(getRestaurant,restaurantImg,menuImgList)){
            System.out.println("식당등록 success restaurnatName : "+ getRestaurant.getRestaurantName());
            return ResponseEntity.ok().body("success");  //식당등록 완료
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 모든 식당 이미지,이름 리스트 전송
    @GetMapping(path = "/getRestaurantData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getRestaurantData() throws IOException {
        Map<String, Object> imgListAndRestaurantNameList = restaurantService.getImgListAndRestaurantNameList();

        return ResponseEntity.ok()
                .body(imgListAndRestaurantNameList);
    }

    // 검색 된 데이터 전달  (식당이름, 식당이미지)
    @GetMapping(path = "/getRestaurantSearchData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getRestaurantSearchData(@RequestParam String restaurantName) throws IOException {
        Map<String, Object> searchDataList = restaurantService.searchByRestaurantNameList(restaurantName);

        return ResponseEntity.ok()
                .body(searchDataList);
    }
}
