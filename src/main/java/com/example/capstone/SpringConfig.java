package com.example.capstone;

import com.example.capstone.repository.MenuRepository;
import com.example.capstone.repository.RestaurantJpaRepository;
import com.example.capstone.repository.RestaurantRepository;
import com.example.capstone.repository.UserJpaRepository;
import com.example.capstone.service.RestaurantService;
import com.example.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final UserJpaRepository userJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final MenuRepository menuRepository;
    @Autowired  //객체하나일때는 생략가능
    public SpringConfig(UserJpaRepository userJpaRepository,RestaurantJpaRepository restaurantJpaRepository,MenuRepository menuRepository) {
        this.userJpaRepository = userJpaRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.menuRepository =menuRepository;
    }

    @Bean
    public UserService userService(){               //스프링 데이터 jpa DB기술일때 사용
        return new UserService(userJpaRepository);
    }
    @Bean
    public RestaurantService restaurantService(){return new RestaurantService(restaurantJpaRepository, menuRepository);}


}
