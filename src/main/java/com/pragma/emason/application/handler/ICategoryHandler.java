package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.domain.model.PageResult;


public interface ICategoryHandler {
    void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO getCategoryByName(String name);

    PageResult<CategoryResponseDTO> getAllCategories(int page, int size, String sortBy, boolean ascending);


}
