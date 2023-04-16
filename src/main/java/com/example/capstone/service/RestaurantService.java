package com.example.capstone.service;

import com.example.capstone.domain.GetRestaurantMenu;
import com.example.capstone.domain.Restaurant;
import com.example.capstone.domain.RestaurantMenu;
import com.example.capstone.domain.User;
import com.example.capstone.repository.RestaurantMenuRepository;
import com.example.capstone.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    private RestaurantMenuRepository restaurantMenuRepository;
    public RestaurantService(RestaurantMenuRepository restaurantMenuRepository) {
        this.restaurantMenuRepository = restaurantMenuRepository;
    }

    //식당 + 메뉴 등록
    public boolean RestaurantRegister(Restaurant restaurant, MultipartFile img, List<GetRestaurantMenu> menuList)throws IOException{
        if(validateDuplicateRestaurant(restaurant.getRestaurantName())) //식당 중복검증
            return false;
        restaurant.setRestaurantImgUrl(imgSave(img)); // 식당이미지 저장 및 url변환
        Restaurant restaurant1 = restaurantRepository.save(restaurant);
        Long restaurantId = restaurant1.getRestaurantId();
        /*
        getRestaurantId(restaurant1) 식당Id 활용
        메뉴 등록 구현 List<restaurantMenu>  완료
        */
        for (GetRestaurantMenu getRestaurantMenu:menuList) {
            menuRegister(getRestaurantMenu,restaurantId); //메뉴등록
        }
        return true;
    }
    //메뉴 등록
    public Long menuRegister(GetRestaurantMenu getRestaurantMenu,Long restaurantId)throws IOException{
        MultipartFile img = getRestaurantMenu.getMenuImg(); //메뉴이미지 가져오기

        // 저장할 메뉴정보
        RestaurantMenu restaurantMenu = new RestaurantMenu();
        restaurantMenu.setMenuName(getRestaurantMenu.getMenuName());
        restaurantMenu.setMenuPrice(getRestaurantMenu.getMenuPrice());
        restaurantMenu.setMenuImgUrl(imgSave(img));
        restaurantMenu.setRestaurantId(restaurantId);
        restaurantMenuRepository.save(restaurantMenu);
        return restaurantMenu.getMenuId();
    }

    public Boolean validateDuplicateRestaurant(String restaurantName) {  ///식당 중복검증
        Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantName(restaurantName);
        if(restaurant.isPresent()){
            return true;  // 중복
        } else {
            return false; // 중복X
        }
    }
    // 이미지 저장
    public String imgSave(MultipartFile img) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File targetFile = new File("C:/Users/goddn/capstoneImg", fileName); //저장되는 위치 (임시:원준집컴퓨터)
        img.transferTo(targetFile);
        String imageUrl = "http://localhost:8080/images/" + fileName;
        return imageUrl;
    }
}
