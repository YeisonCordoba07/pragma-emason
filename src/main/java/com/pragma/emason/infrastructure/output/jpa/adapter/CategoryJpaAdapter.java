package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.infrastructure.exception.NoDataFoundException;
import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistence {
    private final com.pragma.emason.infrastructure.output.jpa.repository.ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;

    @Override
    public void saveCategory(Category category) {

        iCategoryRepository.save(iCategoryEntityMapper.toEntity(category));
    }

    @Override
    public PageResult<Category> getAllCategories(int page, int size, String sortBy, boolean ascending) {

        // Create the Sort object based on the input parameters
        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> categoryEntityPage = iCategoryRepository.findAll(pageable);

        if (categoryEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Maps the list of entities of the list of Domain objects
        List<Category> categories = categoryEntityPage.getContent().stream()
                .map(iCategoryEntityMapper::toCategory)
                .toList();  // Use Stream.toList() to get an immutable list

        // Return a PaginatedResult object that encapsulates the pagination data
        return new PageResult<>(
                categories,
                categoryEntityPage.getNumber(),
                categoryEntityPage.getSize(),
                categoryEntityPage.getTotalElements()
        );
    }

    @Override
    public Category getCategoryByName(String name) {
        return iCategoryEntityMapper.toCategory(iCategoryRepository.findCategoryEntityByName(name));
    }
}