package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.mapper.IBrandRequestMapper;
import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandHandlerTest {

    @Mock
    private IBrandService iBrandService;

    @Mock
    private IBrandRequestMapper iBrandRequestMapper;

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

}