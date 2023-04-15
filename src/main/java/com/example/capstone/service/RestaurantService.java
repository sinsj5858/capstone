package com.example.capstone.service;

import com.example.capstone.domain.Restaurant;
import com.example.capstone.domain.RestaurantMenu;
import com.example.capstone.domain.User;
import com.example.capstone.repository.RestaurantMenuRepository;
import com.example.capstone.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public boolean RestaurantRegister(Restaurant restaurant, List<RestaurantMenu> restaurantMenuList){
        if(validateDuplicateRestaurant(restaurant.getRestaurantName())) //식당 중복검증
            return false;
        Restaurant restaurant1 = restaurantRepository.save(restaurant);
        Long restaurantId = restaurant1.getRestaurantId();
        /*
        getRestaurantId(restaurant1) 식당Id 활용
        메뉴 등록 구현 List<restaurantMenu>  완료
        */
        for (RestaurantMenu restaurantMenu:restaurantMenuList) {
            menuRegister(restaurantMenu,restaurantId); //메뉴등록
        }
        return true;
    }
    //메뉴 등록
    public Long menuRegister(RestaurantMenu restaurantMenu,Long restaurantId){
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
}
