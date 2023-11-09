package com.jack.food.dto;
import java.util.List;
import com.jack.food.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodPage {
    List<FoodItem> foodItemList;
    private Restaurant restaurant;

}
