package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;

import com.pragma.emason.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import java.util.List;


import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository iCategoryRepository;

    @Mock
    private ICategoryEntityMapper iCategoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoriesSuccessfully() {
        // Configure the test data
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Category 1", "Description 1");
        CategoryEntity categoryEntity2 = new CategoryEntity(2, "Category 2", "Description 2");
        List<CategoryEntity> categoryEntities = Arrays.asList(categoryEntity1, categoryEntity2);
        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntities, PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "name")), 10);

        // Configure the respository mock
        when(iCategoryRepository.findAll(any(PageRequest.class))).thenReturn(categoryEntityPage);

        // Configure the mock mapper
        Category category1 = new Category(1, "Category 1", "Description 1");
        Category category2 = new Category(2, "Category 2", "Description 2");
        when(iCategoryEntityMapper.toCategory(categoryEntity1)).thenReturn(category1);
        when(iCategoryEntityMapper.toCategory(categoryEntity2)).thenReturn(category2);

        // Execute the method to be tested
        PageResult<Category> result = categoryJpaAdapter.getAllCategories(0, 2, "name", true);

        // Check the result
        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(2, result.getSize());
        assertEquals(10, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
        assertEquals("Category 2", result.getContent().get(1).getName());
    }
}