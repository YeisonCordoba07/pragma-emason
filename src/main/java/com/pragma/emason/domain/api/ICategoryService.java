package com.pragma.emason.domain.api;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;


public interface ICategoryService {

    void saveCategory(Category category);

    PageResult<Category> getAllCategories(int page, int size);
    Category getCategoryByName(String name);
}
