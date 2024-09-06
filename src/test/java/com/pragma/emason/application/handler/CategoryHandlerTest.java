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
import static org.mockito.Mockito.verify;
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
        // Configure the ICategoryService mock
        Category category1 = new Category("Category 1", "Description 1");
        Category category2 = new Category("Category 2", "Description 2");
        List<Category> categories = Arrays.asList(category1, category2);
        PageResult<Category> categoryPageResult = new PageResult<>(categories, 0, 2, 10);
        when(iCategoryService.getAllCategories(0, 2, "name", true)).thenReturn(categoryPageResult);

        // Configure the ICategoryResponseMapper mock
        CategoryResponseDTO categoryResponseDTO1 = new CategoryResponseDTO(1, "Category 1", "Description 1");
        CategoryResponseDTO categoryResponseDTO2 = new CategoryResponseDTO(2, "Category 2", "Description 2");
        List<CategoryResponseDTO> categoryResponseDTOs = Arrays.asList(categoryResponseDTO1, categoryResponseDTO2);
        when(iCategoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponseDTOs);

        // Execute the method we are testing
        PageResult<CategoryResponseDTO> result = categoryHandler.getAllCategories(0, 2, "name", true);

        // Check the result
        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(2, result.getSize());
        assertEquals(10, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
        assertEquals("Category 2", result.getContent().get(1).getName());
    }

    @Test
    void testGetAllCategoriesMapping() {
        // Mock the input parameters
        int page = 0;
        int size = 3;
        String sortBy = "name";
        boolean ascending = true;

        // Mock the service response
        List<Category> categories = List.of(new Category("name", "description"), new Category( "name", "description"));
        PageResult<Category> categoryPageResult = new PageResult<>(categories, page, size, categories.size());

        // Mock the mapper response
        List<CategoryResponseDTO> categoryResponseDTOList = List.of(new CategoryResponseDTO(1, "name", "description"), new CategoryResponseDTO(2, "name", "description"));

        when(iCategoryService.getAllCategories(page, size, sortBy, ascending)).thenReturn(categoryPageResult);
        when(iCategoryResponseMapper.toResponseList(categoryPageResult.getContent())).thenReturn(categoryResponseDTOList);

        // Call the method
        PageResult<CategoryResponseDTO> result = categoryHandler.getAllCategories(page, size, sortBy, ascending);

        // Verify the results
        assertEquals(categoryResponseDTOList, result.getContent());
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(categories.size(), result.getTotalElements());

        verify(iCategoryService).getAllCategories(page, size, sortBy, ascending);
        verify(iCategoryResponseMapper).toResponseList(categoryPageResult.getContent());
    }

    @Test
    void testGetAllCategoriesInvalidSortBy() {
        int page = 0;
        int size = 3;
        String invalidSortBy = "invalidField";
        boolean ascending = true;

        when(iCategoryService.getAllCategories(page, size, invalidSortBy, ascending))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            categoryHandler.getAllCategories(page, size, invalidSortBy, ascending);
        });

        verify(iCategoryService).getAllCategories(page, size, invalidSortBy, ascending);
    }



}