package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.infrastructure.output.jpa.entity.BrandEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import org.springframework.data.domain.Sort;
import static org.assertj.core.api.Assertions.assertThat;

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


    @Test
    void getAllBrands_WhenTheBrandsAreSortAsc() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "name";
        boolean ascending = true;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));

        BrandEntity brandEntity1 = new BrandEntity();
        brandEntity1.setName("Brand 1");
        brandEntity1.setDescription("Description 1");

        BrandEntity brandEntity2 = new BrandEntity();
        brandEntity2.setName("Brand 2");
        brandEntity2.setDescription("Description 2");

        Page<BrandEntity> brandEntityPage = new PageImpl<>(List.of(brandEntity1, brandEntity2), pageable, 2);

        when(iBrandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(iBrandEntityMapper.toBrand(brandEntity1)).thenReturn(new Brand("Brand 1", "Description 1"));
        when(iBrandEntityMapper.toBrand(brandEntity2)).thenReturn(new Brand("Brand 2", "Description 2"));

        // Act
        PageResult<Brand> result = brandJpaAdapter.getAllBrands(page, size, sortBy, ascending);

        // Assert
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Brand 1");
        assertThat(result.getContent().get(1).getName()).isEqualTo("Brand 2");
        assertThat(result.getTotalElements()).isEqualTo(2);

        verify(iBrandRepository, times(1)).findAll(pageable);
        verify(iBrandEntityMapper, times(1)).toBrand(brandEntity1);
        verify(iBrandEntityMapper, times(1)).toBrand(brandEntity2);
    }

    @Test
    void getAllBrands_WhenTheBrandsAreSortDesc() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "name";
        boolean ascending = false;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));

        BrandEntity brandEntity1 = new BrandEntity();
        brandEntity1.setName("Brand 1");
        brandEntity1.setDescription("Description 1");

        BrandEntity brandEntity2 = new BrandEntity();
        brandEntity2.setName("Brand 2");
        brandEntity2.setDescription("Description 2");

        Page<BrandEntity> brandEntityPage = new PageImpl<>(List.of(brandEntity2, brandEntity1), pageable, 2);

        when(iBrandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(iBrandEntityMapper.toBrand(brandEntity2)).thenReturn(new Brand("Brand 2", "Description 2"));
        when(iBrandEntityMapper.toBrand(brandEntity1)).thenReturn(new Brand("Brand 1", "Description 1"));


        // Act
        PageResult<Brand> result = brandJpaAdapter.getAllBrands(page, size, sortBy, ascending);

        // Assert
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Brand 2");
        assertThat(result.getContent().get(1).getName()).isEqualTo("Brand 1");
        assertThat(result.getTotalElements()).isEqualTo(2);

        verify(iBrandRepository, times(1)).findAll(pageable);
        verify(iBrandEntityMapper, times(1)).toBrand(brandEntity2);
        verify(iBrandEntityMapper, times(1)).toBrand(brandEntity1);
    }
}