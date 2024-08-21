package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.mapper.ICategoryRequestMapper;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryRequestMapper iCategoryRequestMapper;
    private final ICategoryService iCategoryService;

    public CategoryHandler(ICategoryRequestMapper iCategoryRequestMapper, ICategoryService iCategoryService) {
        this.iCategoryRequestMapper = iCategoryRequestMapper;
        this.iCategoryService = iCategoryService;
    }

    @Override
    public void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO) {
        Category category = iCategoryRequestMapper.toCategory(categoryRequestDTO);
        /*if(category.getName().length() > 50){
            throw new IllegalArgumentException("Category name must not exceed 50 characters");
        }
        if(category.getDescription().length() > 90){
            throw new IllegalArgumentException("Category description must not exceed 90 characters");
        }*/
        iCategoryService.saveCategory(category);
    }


}
