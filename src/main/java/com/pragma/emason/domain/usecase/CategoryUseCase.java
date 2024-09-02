package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.exception.CategoryNameAlreadyExistsException;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.ICategoryPersistence;


public class CategoryUseCase implements ICategoryService {
    private final ICategoryPersistence iCategoryPersistence;

    public CategoryUseCase(ICategoryPersistence iCategoryPersistence) {
        this.iCategoryPersistence = iCategoryPersistence;
    }


    @Override
    public void saveCategory(Category category) {
        if(this.iCategoryPersistence.getCategoryByName(category.getName()) != null){
            throw new CategoryNameAlreadyExistsException("A category with this name already exists.");
        }
        this.iCategoryPersistence.saveCategory(category);
    }


    @Override
    public PageResult<Category> getAllCategories(int page, int size, String sortBy, boolean ascending) {
        return iCategoryPersistence.getAllCategories(page, size, sortBy,ascending);
    }


    @Override
    public Category getCategoryByName(String name) {
        return this.iCategoryPersistence.getCategoryByName(name);
    }
}