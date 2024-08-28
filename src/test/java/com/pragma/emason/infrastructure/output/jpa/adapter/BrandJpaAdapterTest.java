package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.infrastructure.output.jpa.entity.BrandEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BrandJpaAdapterTest {

    @Mock
    private IBrandRepository iBrandRepository;

    @Mock
    private IBrandEntityMapper iBrandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_ShouldConvertAndSaveBrand() {
        // Arrange
        Brand brand = new Brand("Brand's name", "Brand's Description");

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Brand's name");
        brandEntity.setDescription("Brand's Description");

        when(iBrandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        // Act
        brandJpaAdapter.saveBrand(brand);

        // Assert
        verify(iBrandEntityMapper).toEntity(brand);
        verify(iBrandRepository).save(brandEntity);
    }

    @Test
    void getBrandByName_ShouldReturnBrandSuccessfully(){
        // Arrange
        String brandName = "Brand's name";
        String brandDescription = "Brand's description";
        int brandId = 1;

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        brandEntity.setName(brandName);
        brandEntity.setDescription(brandDescription);

        Brand brand = new Brand(brandName, brandDescription);


        when(iBrandRepository.findBrandEntityByName(brandName)).thenReturn(brandEntity);
        when(iBrandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        // Act
        Brand resultBrand = brandJpaAdapter.getBrandByName(brandName);

        // Assert
        assertEquals(resultBrand, brand);
        verify(iBrandRepository, times(1)).findBrandEntityByName(brandName);
        verify(iBrandEntityMapper, times(1)).toBrand(brandEntity);
    }

    @Test
    void getBrandByName_ShouldReturnNull(){
        // Arrange
        String brandName = "Brand's name";

        when(iBrandRepository.findBrandEntityByName(brandName)).thenReturn(null);
        when(iBrandEntityMapper.toBrand(null)).thenReturn(null);

        // Act
        Brand resultBrand = brandJpaAdapter.getBrandByName(brandName);

        // Assert
        assertNull(resultBrand);
        verify(iBrandRepository, times(1)).findBrandEntityByName(brandName);
        verify(iBrandEntityMapper, times(1)).toBrand(null);
    }
}