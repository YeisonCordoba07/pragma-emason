package com.pragma.emason.domain.api;

import com.pragma.emason.domain.model.Category;

//Esta clase expone al exterior lo que se puede hacer
public interface CategoryService {

    void saveCategory(Category category);

    //List<Category> getAllCategory();
}
