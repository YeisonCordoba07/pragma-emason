package com.pragma.emason.domain.api;

import com.pragma.emason.domain.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ICategoryService {

    void saveCategory(Category category);

    Page<Category> getAllCategories(int page, int size);
    Category getCategoryByName(String name);
}
