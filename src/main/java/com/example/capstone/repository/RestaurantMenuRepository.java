package com.example.capstone.repository;

import com.example.capstone.domain.RestaurantMenu;

import java.util.List;
import java.util.Optional;

public interface RestaurantMenuRepository {
    RestaurantMenu save(RestaurantMenu restaurantMenu);
    Optional<RestaurantMenu> findByMenuId(Long menuId);
    Optional<RestaurantMenu> findByMenuPrice(String menuPrice);
    Optional<RestaurantMenu> findByMenuImgUrl(String menuImgUrl);
   // Optional<RestaurantMenu> findByRestaurantId(String restaurantId);
    List<RestaurantMenu> findAll();
    List<RestaurantMenu> findListByRestaurantId(String restaurantId);
}
