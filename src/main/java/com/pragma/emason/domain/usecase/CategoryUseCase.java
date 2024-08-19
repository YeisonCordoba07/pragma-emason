package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.CategoryService;
import com.pragma.emason.domain.spi.CategoryRepository;

public class CategoryUseCase implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void saveCategory(Category category) {
        this.categoryRepository.saveCategory(category);
    }
}
