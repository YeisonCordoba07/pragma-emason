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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
}