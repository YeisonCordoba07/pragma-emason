package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryHandler {
    void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO getCategoryByName(String name);

    Page<CategoryResponseDTO> getAllCategories(int page, int size);


}
