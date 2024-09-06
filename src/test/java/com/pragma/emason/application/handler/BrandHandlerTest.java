package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.application.mapper.IBrandRequestMapper;
import com.pragma.emason.application.mapper.IBrandResponseMapper;
import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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
    void getBrandByName_ShouldReturnBrandResponseDTO(){
        String brandName = "Brand's name";
        Brand brand = new Brand();
        brand.setName(brandName);
        brand.setDescription("Brand's description");
        BrandResponseDTO brandResponseDTO = new BrandResponseDTO(1, "Brand's name", "Brand's description");

        when(iBrandService.getBrandByName(brandName)).thenReturn(brand);
        when(iBrandResponseMapper.toResponse(brand)).thenReturn(brandResponseDTO);

        BrandResponseDTO actualResponse = brandHandler.getBrandByName(brandName);
        assertEquals(actualResponse, brandResponseDTO);
        verify(iBrandService, times(1)).getBrandByName(brandName);
        verify(iBrandResponseMapper, times(1)).toResponse(brand);
    }




    @Test
    void getAllBrandsSuccessfully(){
        BrandResponseDTO brandResponseDTO1 = new BrandResponseDTO();
        brandResponseDTO1.setName("Brand 1");
        brandResponseDTO1.setDescription("Description 1");
        BrandResponseDTO brandResponseDTO2 = new BrandResponseDTO();
        brandResponseDTO2.setName("Brand 2");
        brandResponseDTO2.setDescription("Description 2");

        List<BrandResponseDTO> brandResponseDTOList = List.of(brandResponseDTO1, brandResponseDTO2);
        PageResult<BrandResponseDTO> pageResult = new PageResult<>(brandResponseDTOList, 0, 2, 10);


        Brand brand1 = new Brand("Brand 1", "Description 1");
        Brand brand2 = new Brand("Brand 2", "Description 2");

        List<Brand> brands = List.of(brand1, brand2);
        PageResult<Brand> brandPageResult = new PageResult<>(brands, 0, 2, 10);


        when(iBrandService.getAllBrands(0, 2, "name", true)).thenReturn(brandPageResult);
        when(iBrandResponseMapper.toResponseList(brands)).thenReturn(pageResult.getContent());

        PageResult<BrandResponseDTO> result = brandHandler.getAllBrands(0, 2, "name", true);

        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(2, result.getSize());
        assertEquals(10, result.getTotalElements());
        assertEquals("Brand 1", result.getContent().get(0).getName());
        assertEquals("Brand 2", result.getContent().get(1).getName());

    }

}