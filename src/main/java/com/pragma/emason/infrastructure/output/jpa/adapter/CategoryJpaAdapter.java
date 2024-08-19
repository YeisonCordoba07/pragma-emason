package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.spi.CategoryRepository;
import com.pragma.emason.infrastructure.exception.CategoryAlreadyExistsException;
import com.pragma.emason.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.respository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryRepository {
    private final ICategoryRepository iCategoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if(iCategoryRepository.findById(category.getId()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        iCategoryRepository.save(categoryEntityMapper.toEntity(category));
    }
}
