package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.handler.ICategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryRestControllerTest {

    @Mock
    private ICategoryHandler categoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("Yeison");
        requestDTO.setDescription("Yeison's description");

        // Act
        ResponseEntity<Void> response = categoryRestController.saveCategory(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoryHandler, times(1)).saveCategoryInDataBase(any(CategoryRequestDTO.class));
    }

    @Test
    void testGetCategoryByName() {
        // Arrange
        String categoryName = "Yeison";
        String categoryDescription = "Yeison's description";
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setName(categoryName);
        responseDTO.setDescription(categoryDescription);

        when(categoryHandler.getCategoryByName(categoryName)).thenReturn(responseDTO);

        // Act
        ResponseEntity<CategoryResponseDTO> response = categoryRestController.getCategoryByName(categoryName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(categoryHandler, times(1)).getCategoryByName(categoryName);
    }
}