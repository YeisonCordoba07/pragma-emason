package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.spi.ICategoryRepository;
import com.pragma.emason.infrastructure.exception.CategoryAlreadyExistsException;
import com.pragma.emason.infrastructure.exception.NoDataFoundException;
import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    @Override
    public Page<Category> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> categoryEntityPage = iCategoryRepository.findAll(pageable);

        if (categoryEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Mapea cada entidad a un objeto de dominio y devuelve una página de resultados
        return categoryEntityPage.map(iCategoryEntityMapper::toCategory);
    }

    @Override
    public Category getCategoryByName(String name) {
        return iCategoryEntityMapper.toCategory(iCategoryRepository.findCategoryEntityByName(name));
    }
}
