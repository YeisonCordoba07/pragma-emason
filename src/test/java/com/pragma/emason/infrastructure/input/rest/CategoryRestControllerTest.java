package com.pragma.emason.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.handler.ICategoryHandler;
import com.pragma.emason.domain.exception.CategoryNameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerTest {

    @Mock
    private ICategoryHandler iCategoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    void testSaveCategory_whenTheCategoryIsSucessfullySaved() {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("Yeison");
        requestDTO.setDescription("Yeison's description");

        // Act
        ResponseEntity<Void> response = categoryRestController.saveCategory(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(iCategoryHandler, times(1)).saveCategoryInDataBase(any(CategoryRequestDTO.class));
    }


    @Test
    void testGetCategoryByName_whenTheCategoryIsSuccessfullyObtained() {
        // Arrange
        String categoryName = "Yeison";
        String categoryDescription = "Yeison's description";
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setName(categoryName);
        responseDTO.setDescription(categoryDescription);

        when(iCategoryHandler.getCategoryByName(categoryName)).thenReturn(responseDTO);

        // Act
        ResponseEntity<CategoryResponseDTO> response = categoryRestController.getCategoryByName(categoryName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(iCategoryHandler, times(1)).getCategoryByName(categoryName);
    }


    @Test
    void testSaveCategory_whenNameAlreadyExists_shouldReturnConflict() {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("ExistingCategory");
        requestDTO.setDescription("Description for existing category");

        // Simula que se lanza la excepcion cuando se intenta guardar
        doThrow(new CategoryNameAlreadyExistsException("A category with this name already exists."))
                .when(iCategoryHandler).saveCategoryInDataBase(any(CategoryRequestDTO.class));

        // Act & Assert
        Exception exception = assertThrows(CategoryNameAlreadyExistsException.class, () -> {
            categoryRestController.saveCategory(requestDTO);
        });

        // Verifica el mensaje de la excepcion
        assertEquals("A category with this name already exists.", exception.getMessage());
    }


    @Test
    void testSaveCategory_whenNameIsBlank_shouldReturnBadRequest() throws Exception {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("");
        requestDTO.setDescription("Valid description");

        // Act & Assert
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testSaveCategory_whenDescriptionIsBlank_shouldReturnBadRequest() throws Exception {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("Yeison");
        requestDTO.setDescription("");

        // Act & Assert
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testSaveCategory_whenNameIsNull_shouldReturnBadRequest() throws Exception {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setDescription("Valid description");

        // Act & Assert
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testSaveCategory_whenDescriptionIsNull_shouldReturnBadRequest() throws Exception {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("Yeison");

        // Act & Assert
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testSaveCategory_whenNameExceedsMaxLength_shouldReturnBadRequest() throws Exception {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("Y".repeat(51));
        requestDTO.setDescription("Valid description");

        // Act & Assert
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testSaveCategory_whenDescriptionExceedsMaxLength_shouldReturnBadRequest() throws Exception {
        // Arrange
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setName("Yeison");
        requestDTO.setDescription("D".repeat(91));

        // Act & Assert
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}