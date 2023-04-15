package com.example.capstone.repository;

import com.example.capstone.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long>,RestaurantRepository{
    @Override
    Optional<Restaurant> findByRestaurantId(Long restaurantId);
    @Override
    Optional<Restaurant> findByRestaurantImgUrl(String RestaurantImgUrl);
    @Override
    Optional<Restaurant> findByRestaurantName(String restaurantName);
    @Override
    Optional<Restaurant> findByRestaurantLocation(String restaurantLocation);
    @Override
    Optional<Restaurant> findByRestaurantOperatingTime(String operatingTime);
}
