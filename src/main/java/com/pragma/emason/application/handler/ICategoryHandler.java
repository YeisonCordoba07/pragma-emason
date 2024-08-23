package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;

import java.util.List;

public interface ICategoryHandler {
    void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO getCategoryByName(String name);

    List<CategoryResponseDTO> getAllCategories();


}
