package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;


public interface ICategoryPersistence {

    void saveCategory(Category category);

    PageResult<Category> getAllCategories(int page, int size, String sortBy, boolean ascending);
    Category getCategoryByName(String name);
}
