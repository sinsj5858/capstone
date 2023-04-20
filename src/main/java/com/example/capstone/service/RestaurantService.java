package com.example.capstone.service;

import com.example.capstone.domain.GetMenu;
import com.example.capstone.domain.RestaurantRequest;
import com.example.capstone.domain.Restaurant;
import com.example.capstone.domain.Menu;
import com.example.capstone.repository.MenuRepository;
import com.example.capstone.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
    public boolean RestaurantRegister(RestaurantRequest restaurantRequest, MultipartFile restaurantImg, List<MultipartFile> menuImgList)throws IOException{
        log.debug("debug log={}", "RestaurantRegister메소드");
        if(validateDuplicateRestaurant(restaurantRequest.getRestaurantName())) //식당 중복검증
            return false;
        // 식당 저장 객체
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantImgUrl(imgSave(restaurantImg, restaurantRequest.getRestaurantName())); // 식당이미지 저장 및 url변환
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setRestaurantLocation(restaurantRequest.getRestaurantLocation());
        restaurant.setRestaurantOperatingTime(restaurantRequest.getRestaurantOperatingTime());
        Restaurant restaurant1 = restaurantRepository.save(restaurant);

        /*
        getRestaurantId(restaurant1) 식당Id 활용
        메뉴 등록 구현 List<restaurantMenu>  완료
        */
        Long restaurantId = restaurant1.getRestaurantId();
        List<GetMenu> menuList = restaurantRequest.getMenuList();

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
//        String fileName = UUID.randomUUID().toString() + ".jpg";
//        File targetFile = new File("C:/Users/goddn/capstoneImg", fileName); //저장되는 위치 (임시:원준집컴퓨터)
//        img.transferTo(targetFile);
//        String imageUrl = "http://localhost:8080/images/" + fileName;
        String imageUrl = filePath;
        log.debug("debug log={}", "imgSave저장완료");
        return imageUrl;
    }
}
