package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistence iCategoryPersistence;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoriesSuccessfully() {
        Category category1 = new Category("Category 1", "Description 1");
        Category category2 = new Category("Category 2", "Description 2");

        List<Category> listCategories = Arrays.asList(category1, category2);
        PageResult<Category> pageResult = new PageResult<>(listCategories, 0, 2, 10);

        when(iCategoryPersistence.getAllCategories(0, 2, "name", true)).thenReturn(pageResult);

        PageResult<Category> result = iCategoryPersistence.getAllCategories(0, 2, "name", true);

        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(2, result.getSize());
        assertEquals(10, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
        assertEquals("Category 2", result.getContent().get(1).getName());

    }

    @Test
    void testGetAllCategories_PageTooHigh_ThrowsNoDataFoundException() {
        // Arrange
        int highPageNumber = 100;  // A very high page number
        int pageSize = 5;
        String sortBy = "name";
        boolean ascending = true;

        when(iCategoryPersistence.getAllCategories(highPageNumber, pageSize, sortBy, ascending))
                .thenThrow(new NoDataFoundException());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> {
            iCategoryPersistence.getAllCategories(highPageNumber, pageSize, sortBy, ascending);
        });

        verify(iCategoryPersistence).getAllCategories(highPageNumber, pageSize, sortBy, ascending);
    }

    @Test
    void testGetAllCategories_InvalidSortBy_ThrowsException() {
        // Arrange
        int page = 0;
        int pageSize = 5;
        String invalidSortBy = "invalidField";
        boolean ascending = true;

        when(iCategoryPersistence.getAllCategories(page, pageSize, invalidSortBy, ascending))
                .thenThrow(new IllegalArgumentException("Invalid sort field"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            iCategoryPersistence.getAllCategories(page, pageSize, invalidSortBy, ascending);
        });

        verify(iCategoryPersistence).getAllCategories(page, pageSize, invalidSortBy, ascending);
    }
}