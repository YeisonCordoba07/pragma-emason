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


    public CategoryHandler(
            ICategoryRequestMapper iCategoryRequestMapper,
            ICategoryService iCategoryService,
            ICategoryResponseMapper iCategoryResponseMapper) {

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
    public PageResult<CategoryResponseDTO> getAllCategories(int page, int size, String sortBy, boolean ascending) {
        // Gets the paginated result of the service
        PageResult<Category> categoryPage = iCategoryService.getAllCategories(page,  size,  sortBy, ascending);

        // Use the mapper to convert each Category to CategoryResponseDTO
        List<CategoryResponseDTO> categoryResponseDTOList = iCategoryResponseMapper
                .toResponseList(categoryPage.getContent());

        // Create a new PaginatedResult for the DTOs
        return new PageResult<>(
                categoryResponseDTOList,
                categoryPage.getPage(),
                categoryPage.getSize(),
                categoryPage.getTotalElements()
        );
    }

}