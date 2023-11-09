package com.jack.food.controller;

import com.jack.food.dto.FoodItemDTO;
import com.jack.food.dto.FoodPage;
import com.jack.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;

    @PostMapping("/addFood")
    public ResponseEntity<FoodItemDTO> addFood(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO foodAdded = foodService.addFood(foodItemDTO);
        return new ResponseEntity<>(foodAdded, HttpStatus.CREATED);
    }

    @GetMapping("/getRestaurantAndFoodById/{restaurantId}")
    public ResponseEntity<FoodPage> getRestaurantWithFood(@PathVariable Integer restaurantId){
        FoodPage foodPage = foodService.getFoodDetails(restaurantId);
        return new ResponseEntity<>(foodPage,HttpStatus.OK);
    }



}
