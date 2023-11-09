package com.jack.food.service;

import com.jack.food.dto.FoodItemDTO;
import com.jack.food.dto.FoodPage;
import com.jack.food.dto.Restaurant;
import com.jack.food.entity.FoodItem;
import com.jack.food.mapper.FoodItemMapper;
import com.jack.food.repo.FoodItemRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFood(FoodItemDTO foodItemDTO) {
        FoodItem foodAdded = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodAdded);
    }

    public FoodPage getFoodDetails(Integer restaurantId) {

        List<FoodItem> foodItemList = getFoodItemList(restaurantId);
        Restaurant restaurant = getRestaurantFromRestaurantMS(restaurantId);
        return createFoodPage(foodItemList, restaurant);


    }

    private Restaurant getRestaurantFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/getById/" + restaurantId, Restaurant.class);
    }

    private FoodPage createFoodPage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodPage foodPage = new FoodPage();
        foodPage.setFoodItemList(foodItemList);
        foodPage.setRestaurant(restaurant);
        return foodPage;

    }

    private List<FoodItem> getFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
