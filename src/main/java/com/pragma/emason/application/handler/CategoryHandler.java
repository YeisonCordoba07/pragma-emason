package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.mapper.ICategoryRequestMapper;
import com.pragma.emason.application.mapper.ICategoryResponseMapper;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryRequestMapper iCategoryRequestMapper;
    private final ICategoryService iCategoryService;
    private final ICategoryResponseMapper iCategoryResponseMapper;

    public CategoryHandler(ICategoryRequestMapper iCategoryRequestMapper, ICategoryService iCategoryService, ICategoryResponseMapper iCategoryResponseMapper) {
        this.iCategoryRequestMapper = iCategoryRequestMapper;
        this.iCategoryService = iCategoryService;
        this.iCategoryResponseMapper = iCategoryResponseMapper;
    }

    @Override
    public void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO) {
        Category category = iCategoryRequestMapper.toCategory(categoryRequestDTO);
        this.iCategoryService.saveCategory(category);
    }

    @Override
    public CategoryResponseDTO getCategoryByName(String name) {
        return iCategoryResponseMapper.toResponse(iCategoryService.getCategoryByName(name));
    }


}
