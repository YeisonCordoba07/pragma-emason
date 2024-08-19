package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryRequest;

public interface ICategoryHandler {
    void saveCategoryInDataBase(CategoryRequest categoryRequest);

}
