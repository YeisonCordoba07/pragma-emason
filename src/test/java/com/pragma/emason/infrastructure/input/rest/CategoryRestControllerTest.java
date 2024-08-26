package com.pragma.emason.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.handler.ICategoryHandler;
import com.pragma.emason.domain.exception.CategoryNameAlreadyExistsException;
import com.pragma.emason.domain.model.PageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

        // Simulates that the exception is thrown when tryin to save
        doThrow(new CategoryNameAlreadyExistsException("A category with this name already exists."))
                .when(iCategoryHandler).saveCategoryInDataBase(any(CategoryRequestDTO.class));

        // Act & Assert
        Exception exception = assertThrows(CategoryNameAlreadyExistsException.class, () -> {
            categoryRestController.saveCategory(requestDTO);
        });

        // Check the exception message
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




    @Test
    void getCategoriesSuccessfully() throws Exception {
        PageResult<CategoryResponseDTO> pageResult = new PageResult<>();
        pageResult.setContent(Arrays.asList(
                new CategoryResponseDTO(0, "Category 1", "Description 1"),
                new CategoryResponseDTO(1, "Category 2", "Description 2"),
                new CategoryResponseDTO(2, "Category 3", "Description 3")
        ));
        pageResult.setTotalPages(1);
        pageResult.setTotalElements(3);
        pageResult.setPage(0);
        pageResult.setSize(3);

        Mockito.when(iCategoryHandler.getAllCategories(anyInt(), anyInt(), anyString(), anyBoolean()))
                .thenReturn(pageResult);

        // Make the request and check the results
        mockMvc.perform(get("/category/getAll")
                        .param("page", "0")
                        .param("size", "3")
                        .param("sortBy", "name")
                        .param("ascending", "false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].name").value("Category 1"))
                .andExpect(jsonPath("$.content[1].name").value("Category 2"))
                .andExpect(jsonPath("$.content[2].name").value("Category 3"))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(3));

    }


    @Test
    void getCategories_PageNumberGreaterThanAvailable_ReturnsEmptyPage() throws Exception {
        // Prepare the simulated result of the getAllCategories call
        PageResult<CategoryResponseDTO> pageResult = new PageResult<>();
        pageResult.setContent(Collections.emptyList()); // Empty page
        pageResult.setTotalPages(2); // Only one page available
        pageResult.setTotalElements(6); // Three elements in total
        pageResult.setPage(10); // Requested page that does not exist
        pageResult.setSize(3);

        Mockito.when(iCategoryHandler.getAllCategories(anyInt(), anyInt(), anyString(), anyBoolean()))
                .thenReturn(pageResult);

        // Perform the request and verify the results
        mockMvc.perform(get("/category/getAll")
                        .param("page", "10") // Page number greater than available
                        .param("size", "3")
                        .param("sortBy", "name")
                        .param("ascending", "false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isEmpty()) // Verify that the content is empty
                .andExpect(jsonPath("$.totalPages").value(2)) // Only one page available
                .andExpect(jsonPath("$.totalElements").value(6)) // Three elements in total
                .andExpect(jsonPath("$.page").value(10)) // Requested page
                .andExpect(jsonPath("$.size").value(3));
    }


}