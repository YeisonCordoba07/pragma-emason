package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Category;


public interface ICategoryRepository {

    void saveCategory(Category category);

    //List<Category> getAllCategory();
    Category getCategoryByName(String name);
}