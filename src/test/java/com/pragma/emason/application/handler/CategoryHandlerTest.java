package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.mapper.ICategoryResponseMapper;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryHandlerTest {

    @Mock
    private ICategoryService iCategoryService;

    @Mock
    private ICategoryResponseMapper iCategoryResponseMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoriesTest() {
        // Configurar el mock de ICategoryService
        Category category1 = new Category(1, "Category 1", "Description 1");
        Category category2 = new Category(2, "Category 2", "Description 2");
        List<Category> categories = Arrays.asList(category1, category2);
        PageResult<Category> categoryPageResult = new PageResult<>(categories, 0, 2, 10);
        when(iCategoryService.getAllCategories(0, 2, "name", true)).thenReturn(categoryPageResult);

        // Configurar el mock de ICategoryResponseMapper
        CategoryResponseDTO categoryResponseDTO1 = new CategoryResponseDTO(1, "Category 1", "Description 1");
        CategoryResponseDTO categoryResponseDTO2 = new CategoryResponseDTO(2, "Category 2", "Description 2");
        List<CategoryResponseDTO> categoryResponseDTOs = Arrays.asList(categoryResponseDTO1, categoryResponseDTO2);
        when(iCategoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponseDTOs);

        // Ejecutar el método que estamos probando
        PageResult<CategoryResponseDTO> result = categoryHandler.getAllCategories(0, 2, "name", true);

        // Verificar el resultado
        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(2, result.getSize());
        assertEquals(10, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
        assertEquals("Category 2", result.getContent().get(1).getName());
    }

}