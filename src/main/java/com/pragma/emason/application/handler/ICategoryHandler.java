package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;

public interface ICategoryHandler {
    void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO getCategoryByName(String name);


}
