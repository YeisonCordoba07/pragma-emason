package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequestDTO;

public interface ICategoryHandler {
    void saveCategoryInDataBase(CategoryRequestDTO categoryRequestDTO);

}
