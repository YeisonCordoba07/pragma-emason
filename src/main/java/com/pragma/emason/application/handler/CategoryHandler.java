package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequest;
import com.pragma.emason.application.mapper.CategoryRequestMapper;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryService categoryService;

    public CategoryHandler(CategoryRequestMapper categoryRequestMapper, CategoryService categoryService) {
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void saveCategoryInDataBase(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        if(category.getName().length() > 50){
            throw new IllegalArgumentException("Category name must not exceed 50 characters");
        }
        if(category.getDescription().length() > 90){
            throw new IllegalArgumentException("Category description must not exceed 90 characters");
        }
        categoryService.saveCategory(category);
    }


}
