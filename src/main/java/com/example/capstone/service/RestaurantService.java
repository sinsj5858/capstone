package com.example.capstone.service;

import com.example.capstone.domain.GetMenu;
import com.example.capstone.domain.GetRestaurant;
import com.example.capstone.domain.Restaurant;
import com.example.capstone.domain.Menu;
import com.example.capstone.repository.MenuRepository;
import com.example.capstone.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Transactional
public class RestaurantService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private RestaurantRepository restaurantRepository;
    private MenuRepository menuRepository;
    public RestaurantService(RestaurantRepository restaurantRepository,MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }
    //식당 + 메뉴 등록
    public boolean RestaurantRegister(GetRestaurant getRestaurant, MultipartFile restaurantImg, List<MultipartFile> menuImgList)throws IOException{
        log.debug("debug log={}", "RestaurantRegister메소드");
        if(validateDuplicateRestaurant(getRestaurant.getRestaurantName())) //식당 중복검증
            return false;
        // 식당 저장 객체
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantImgUrl(imgSave(restaurantImg, getRestaurant.getRestaurantName())); // 식당이미지 저장 및 url변환
        restaurant.setRestaurantName(getRestaurant.getRestaurantName());
        restaurant.setRestaurantLocation(getRestaurant.getRestaurantLocation());
        restaurant.setRestaurantOperatingTime(getRestaurant.getRestaurantOperatingTime());
        restaurant.setRestaurantCategory(getRestaurant.getRestaurantCategory());
        Restaurant restaurant1 = restaurantRepository.save(restaurant);

        /*
        getRestaurantId(restaurant1) 식당Id 활용
        메뉴 등록 구현 List<restaurantMenu>  완료
        */
        Long restaurantId = restaurant1.getRestaurantId();
        List<GetMenu> menuList = getRestaurant.getMenuList();

        // menuList,imgList 크기만큼 반복
        int count = menuList.size();
        for (int i = 0 ; i<count;i++) {
            menuRegister(menuList.get(i),menuImgList.get(i),restaurantId); //메뉴등록
        }
        return true;
    }
    //메뉴 등록
    public Long menuRegister(GetMenu getMenu, MultipartFile menuImg, Long restaurantId)throws IOException{
        log.debug("debug log={}", "menuRegister메소드");
        // 저장할 메뉴정보
        Menu menu = new Menu();
        menu.setMenuName(getMenu.getMenuName());
        menu.setMenuPrice(getMenu.getMenuPrice());
        menu.setMenuImgUrl(imgSave(menuImg,restaurantRepository.findByRestaurantId(restaurantId).get().getRestaurantName()));
        menu.setRestaurantId(restaurantId);
        //저장
        menuRepository.save(menu);
        return menu.getMenuId();
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
    public String imgSave(MultipartFile img,String restaurantName) throws IOException {
        log.debug("debug log={}", "imgSave메소드");
        String fileName = StringUtils.cleanPath(img.getOriginalFilename());
        String fileDirectory = "C:/Users/goddn/capstoneImg/" + restaurantName; // 식당 이름을 디렉토리 이름으로 사용
        String filePath = fileDirectory + "/" + fileName; // DB에 저장될 이미지파일의 최종주소
        try {
            // 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(Paths.get(fileDirectory));
            // 파일 저장
            Files.write(Paths.get(filePath), img.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageUrl = filePath;
        log.debug("debug log={}", "imgSave저장완료");
        return imageUrl;
    }

    public Map<String, Object> getImgListAndRestaurantNameList()throws IOException{
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<String> imageUrlList = new ArrayList<>();
        List<String> restaurantNameList = new ArrayList<>();

        // 식당객체에서 필요한 태용 추출
        for (Restaurant restaurant : restaurantList) {
            restaurantNameList.add(restaurant.getRestaurantName());
            imageUrlList.add(restaurant.getRestaurantImgUrl());
        }
        // 이미지 리스트
        List<String> imageList = new ArrayList<>();
        // 이미지 byte 변환후 Sting으로 인코딩 후 리스트에 저장
        for (String imageUrl : imageUrlList) {
            File imageFile = new File(imageUrl);
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            String imageData = Base64.getEncoder().encodeToString(imageBytes);
            imageList.add(imageData);
        }
        // 보낼정보 map에 저장
        Map<String, Object> data = new HashMap<>();
        data.put("imageList", imageList);
        data.put("restaurantNameList", restaurantNameList);
        return data;
    }
    public Map<String, Object> searchByRestaurantNameList(String restaurantName)throws IOException{
        List<Restaurant> restaurantList = restaurantRepository.findByRestaurantNameContaining(restaurantName);
        List<String> imageUrlList = new ArrayList<>();
        List<String> restaurantNameList = new ArrayList<>();

        // 식당객체에서 필요한 태용 추출
        for (Restaurant restaurant : restaurantList) {
            restaurantNameList.add(restaurant.getRestaurantName());
            imageUrlList.add(restaurant.getRestaurantImgUrl());
        }
        // 이미지 리스트
        List<String> imageList = new ArrayList<>();
        // 이미지 byte 변환후 Sting으로 인코딩 후 리스트에 저장
        for (String imageUrl : imageUrlList) {
            File imageFile = new File(imageUrl);
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            String imageData = Base64.getEncoder().encodeToString(imageBytes);
            imageList.add(imageData);
        }
        // 보낼정보 map에 저장
        Map<String, Object> data = new HashMap<>();
        data.put("imageList", imageList);
        data.put("restaurantNameList", restaurantNameList);
        return data;
    }
}
