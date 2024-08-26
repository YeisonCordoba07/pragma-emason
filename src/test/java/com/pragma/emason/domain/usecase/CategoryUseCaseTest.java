package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
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
        Category category1 = new Category(1, "Category 1", "Description 1");
        Category category2 = new Category(2, "Category 2", "Description 2");

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
}