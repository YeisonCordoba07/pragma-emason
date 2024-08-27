package com.pragma.emason.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.handler.IBrandHandler;
import com.pragma.emason.domain.exception.BrandNameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandRestControllerTest {

    @Mock
    private IBrandHandler iBrandHandler;

    @InjectMocks
    private BrandRestController brandRestController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(brandRestController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    void saveBrand_whenTheBrandIsSuccessfullySaved() {
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setName("Brand's name");
        brandRequestDTO.setDescription("Brand's description");

        // Act
        ResponseEntity<Void> response = brandRestController.saveBrand(brandRequestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(iBrandHandler, times(1)).saveBrandInDataBase(any(BrandRequestDTO.class));
    }


    @Test
    void saveBrand_whenNameAlreadyExists_shouldReturnConflict(){
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setName("Brand's name");
        brandRequestDTO.setDescription("Brand's description");

        doThrow(new BrandNameAlreadyExistsException("A brand with this name already exists."))
                .when(iBrandHandler).saveBrandInDataBase(any(BrandRequestDTO.class));


        // Act & Assert
        Exception exception = assertThrows(BrandNameAlreadyExistsException.class, () ->{
            brandRestController.saveBrand(brandRequestDTO);
        });

        // Check the exception message
        assertEquals("A brand with this name already exists.", exception.getMessage());
    }


    @Test
    void saveBrand_whenNameIsBlank_shouldReturnBadRequest() throws Exception {
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setName("");
        brandRequestDTO.setDescription("Valid description");

        // Act & Assert
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void saveBrand_whenDescriptionIsBlank_shouldReturnBadRequest() throws Exception {
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setName("Brand's name");
        brandRequestDTO.setDescription("");

        // Act & Assert
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void saveBrand_whenNameIsNull_shouldReturnBadRequest() throws Exception {
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setDescription("Valid description");

        // Act & Assert
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void saveBrand_whenDescriptionIsNull_shouldReturnBadRequest() throws Exception {
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setName("Brand's name");

        // Act & Assert
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void saveBrand_whenNameExceedsMaxLength_shouldReturnBadRequest() throws Exception {
        // Arrange
        BrandRequestDTO requestDTO = new BrandRequestDTO();
        requestDTO.setName("B".repeat(51));
        requestDTO.setDescription("Valid description");

        // Act & Assert
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void saveBrand_whenDescriptionExceedsMaxLength_shouldReturnBadRequest() throws Exception {
        // Arrange
        BrandRequestDTO requestDTO = new BrandRequestDTO();
        requestDTO.setName("Brand's name");
        requestDTO.setDescription("D".repeat(121));

        // Act & Assert
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}