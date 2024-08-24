package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ICategoryRepository {

    void saveCategory(Category category);

    Page<Category> getAllCategories(int page, int size);
    Category getCategoryByName(String name);
}
