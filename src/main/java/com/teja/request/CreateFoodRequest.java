package com.teja.request;

import com.teja.model.Category;
import com.teja.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private Boolean vegetarian;
    private Boolean seasonal;
    private List<IngredientsItem> ingredients;

}
