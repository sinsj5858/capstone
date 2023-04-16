package com.example.capstone.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// Retrofit을 통해 가져올 데이터 (이미지 제외 이미지는 따로 처리)
public class GetRestaurant {
    private MultipartFile restaurantImg; // 식당 이미지
    private String restaurantName;   // 식당이름 정보
    private String restaurantLocation;// 식당 위치 정보
    private String restaurantOperatingTime; // 운영시간 정보
    private List<GetRestaurantMenu> menuList; // 추가할메뉴리스트 저장시 위에 값들만 저장

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantOperatingTime() {
        return restaurantOperatingTime;
    }

    public void setRestaurantOperatingTime(String restaurantOperatingTime) {
        this.restaurantOperatingTime = restaurantOperatingTime;
    }


    public MultipartFile getRestaurantImg() {
        return restaurantImg;
    }

    public void setRestaurantImg(MultipartFile restaurantImg) {
        this.restaurantImg = restaurantImg;
    }
    public List<GetRestaurantMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<GetRestaurantMenu> menuList) {
        this.menuList = menuList;
    }
}
