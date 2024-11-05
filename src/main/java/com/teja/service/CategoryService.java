package com.teja.service;

import com.teja.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createdCategory(String name, Long userId) throws Exception;
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
    public Category findCategoryById(Long id) throws Exception;

}
