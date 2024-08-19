package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Category;


public interface CategoryRepository {

    void saveCategory(Category category);

    //List<Category> getAllCategory();
}
