package com.example.capstone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.multipart.MultipartFile;


public class GetRestaurantMenu {
    private String menuName;
    private String menuPrice;
    private MultipartFile menuImg;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
    public MultipartFile getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(MultipartFile menuImg) {
        this.menuImg = menuImg;
    }
}
