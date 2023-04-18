package com.example.capstone.domain;

import org.springframework.web.multipart.MultipartFile;


public class GetMenu {
    private String menuName;
    private String menuPrice;

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
}
