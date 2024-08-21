package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.spi.ICategoryRepository;
import com.pragma.emason.infrastructure.exception.CategoryAlreadyExistsException;
import com.pragma.emason.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryRepository {
    private final com.pragma.emason.infrastructure.output.jpa.repository.ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if(iCategoryRepository.findById(category.getId()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        iCategoryRepository.save(iCategoryEntityMapper.toEntity(category));
    }
}
