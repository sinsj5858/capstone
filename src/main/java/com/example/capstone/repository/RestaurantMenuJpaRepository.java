package com.example.capstone.repository;

import com.example.capstone.domain.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantMenuJpaRepository extends JpaRepository<RestaurantMenu, Long>,RestaurantMenuRepository{
    @Override
    Optional<RestaurantMenu> findByMenuId(Long menuId);
    @Override
    Optional<RestaurantMenu> findByMenuPrice(String menuPrice);
    @Override
    Optional<RestaurantMenu> findByMenuImgUrl(String menuImgUrl);
    @Override
    List<RestaurantMenu> findListByRestaurantId(String restaurantId);
    //Optional<RestaurantMenu> findByRestaurantId(String restaurantId);
}
