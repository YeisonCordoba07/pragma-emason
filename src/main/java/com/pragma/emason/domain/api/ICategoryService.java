package com.pragma.emason.domain.api;

import com.pragma.emason.domain.model.Category;

import java.util.List;


public interface ICategoryService {

    void saveCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
