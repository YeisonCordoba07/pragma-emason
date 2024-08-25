package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.mapper.ICategoryRequestMapper;
import com.pragma.emason.application.mapper.ICategoryResponseMapper;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public PageResult<CategoryResponseDTO> getAllCategories(int page, int size) {
        // Obtiene el resultado paginado del servicio
        PageResult<Category> categoryPage = iCategoryService.getAllCategories(page, size);

        // Usa el mapper para convertir cada Category a CategoryResponseDTO
        List<CategoryResponseDTO> categoryResponseDTOList = iCategoryResponseMapper.toResponseList(categoryPage.getContent());

        // Crea un nuevo PaginatedResult para los DTOs
        return new PageResult<>(
                categoryResponseDTOList,
                categoryPage.getPage(),
                categoryPage.getSize(),
                categoryPage.getTotalElements()

        );
    }



}
