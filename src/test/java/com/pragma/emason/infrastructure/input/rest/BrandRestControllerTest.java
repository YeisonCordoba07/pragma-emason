package com.pragma.emason.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.application.handler.IBrandHandler;
import com.pragma.emason.domain.exception.BrandNameAlreadyExistsException;
import com.pragma.emason.domain.model.PageResult;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


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

    @Test
    void getBrandByName_ShouldReturnBrandResponseDTO() {
        // Arrange
        String brandName = "BrandTest";
        BrandResponseDTO expectedResponse = new BrandResponseDTO(brandName, "Brand Description");

        when(iBrandHandler.getBrandByName(brandName)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<BrandResponseDTO> response = brandRestController.getBrandByName(brandName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(iBrandHandler, times(1)).getBrandByName(brandName);
    }

    @Test
    void getBrandByName_ShouldReturnNotFoundWhenBrandDoesNotExist() {
        // Arrange
        String brandName = "NonExistentBrand";

        when(iBrandHandler.getBrandByName(brandName)).thenReturn(null);

        // Act
        ResponseEntity<BrandResponseDTO> response = brandRestController.getBrandByName(brandName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(iBrandHandler, times(1)).getBrandByName(brandName);
    }






    @Test
    void getAllBrands_WhenTheBrandsAreSuccessfullyObtained() throws Exception {
        // Arrange
        PageResult<BrandResponseDTO> mockPageResult = new PageResult<>();
        // Simulate that there are 5 total pages
        mockPageResult.setTotalPages(2);
        mockPageResult.setPage(0);
        mockPageResult.setSize(1);
        mockPageResult.setTotalElements(2);
        mockPageResult.setContent(List.of(new BrandResponseDTO("Brand1", "Description1"),
                new BrandResponseDTO("Brand2", "Description2")));

        when(iBrandHandler.getAllBrands(0, 1, "name", true)).thenReturn(mockPageResult);

        // Act & Assert
        mockMvc.perform(get("/brand/getAll")
                        .param("page", "0")
                        .param("size", "1")
                        .param("sortBy", "name")
                        .param("ascending", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

   @Test
    void testGetAllBrands_PageOutOfRange() throws Exception {
        // Arrange
        PageResult<BrandResponseDTO> mockPageResult = new PageResult<>();
        // Simulate that there are 5 total pages
        mockPageResult.setTotalPages(5);

        when(iBrandHandler.getAllBrands(10, 10, "name", true)).thenReturn(mockPageResult);

        // Act & Assert
        mockMvc.perform(get("/brand/getAll")
                        .param("page", "10")
                        .param("size", "10")
                        .param("sortBy", "name")
                        .param("ascending", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}