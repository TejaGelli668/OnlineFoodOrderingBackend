package com.teja.controller;

import com.teja.model.Category;
import com.teja.model.Food;
import com.teja.model.Restaurant;
import com.teja.model.User;
import com.teja.request.CreateFoodRequest;
import com.teja.service.FoodService;
import com.teja.service.RestaurantService;
import com.teja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(
            @RequestBody CreateFoodRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        // Extract user from JWT token
        User user = userService.findUserByJwtToken(jwt);

        // Check if user has admin role
        if (user.getRole() != User.USER_ROLE.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Retrieve restaurant by ID from request
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        // Create the food item
        Food food = foodService.createFood(req, req.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        // Extract user from JWT token
        User user = userService.findUserByJwtToken(jwt);

        // Check if user has admin role
        if (user.getRole() != User.USER_ROLE.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Delete the food item
        foodService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvailabilityStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        // Extract user from JWT token
        User user = userService.findUserByJwtToken(jwt);

        // Check if user has admin role
        if (user.getRole() != User.USER_ROLE.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Update availability status
        Food updatedFood = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(updatedFood, HttpStatus.OK);
    }

}
