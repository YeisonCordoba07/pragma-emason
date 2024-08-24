package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.exception.CategoryNameAlreadyExistsException;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.spi.ICategoryRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoryUseCase implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryUseCase(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }


    @Override
    public void saveCategory(Category category) {
        if(this.iCategoryRepository.getCategoryByName(category.getName()) != null){
            throw new CategoryNameAlreadyExistsException("A category with this name already exists.");
        }
        this.iCategoryRepository.saveCategory(category);
    }

    @Override
    public Page<Category> getAllCategories(int page, int size) {
        return iCategoryRepository.getAllCategories(page, size);
    }

    @Override
    public Category getCategoryByName(String name) {
        return this.iCategoryRepository.getCategoryByName(name);
    }
}
