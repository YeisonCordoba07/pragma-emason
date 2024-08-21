package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.spi.ICategoryRepository;

public class CategoryUseCase implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryUseCase(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    //Aqui van las validaciones o la logica de negocio
    @Override
    public void saveCategory(Category category) {
        if(this.iCategoryRepository.getCategoryByName(category.getName()) != null){
            return;
        }
        this.iCategoryRepository.saveCategory(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return this.iCategoryRepository.getCategoryByName(name);
    }
}
