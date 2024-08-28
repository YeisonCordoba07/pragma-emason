package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.application.mapper.IBrandRequestMapper;
import com.pragma.emason.application.mapper.IBrandResponseMapper;
import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandHandlerTest {

    @Mock
    private IBrandService iBrandService;

    @Mock
    private IBrandRequestMapper iBrandRequestMapper;

    @Mock
    private IBrandResponseMapper iBrandResponseMapper;

    @InjectMocks
    private BrandHandler brandHandler;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveBrandInDataBase_ShouldCallMapperAndService() {
        // Arrange
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        Brand brand = new Brand();
        when(iBrandRequestMapper.toBrand(brandRequestDTO)).thenReturn(brand);

        // Act
        brandHandler.saveBrandInDataBase(brandRequestDTO);

        // Assert
        verify(iBrandRequestMapper).toBrand(brandRequestDTO);
        verify(iBrandService).saveBrand(brand);
    }

    @Test
    void getBrandByName_When(){
        String brandName = "Brand's name";
        Brand brand = new Brand("Brand's name", "Brand's description");
        BrandResponseDTO brandResponseDTO = new BrandResponseDTO("Brand's name", "Brand's description");

        when(iBrandService.getBrandByName(brandName)).thenReturn(brand);
        when(iBrandResponseMapper.toResponse(brand)).thenReturn(brandResponseDTO);

        BrandResponseDTO actualResponse = brandHandler.getBrandByName(brandName);
        assertEquals(actualResponse, brandResponseDTO);
        verify(iBrandService, times(1)).getBrandByName(brandName);
        verify(iBrandResponseMapper, times(1)).toResponse(brand);
    }

}