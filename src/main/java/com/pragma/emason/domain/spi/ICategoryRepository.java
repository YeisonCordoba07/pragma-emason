package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Category;

import java.util.List;


public interface ICategoryRepository {

    void saveCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
