package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.exception.BrandNameAlreadyExistsException;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.IBrandPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistence iBrandPersistence;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void saveBrand_BrandDoesNotExist_ShouldSaveBrand() {
        // Arrange
        String brandName = "NewBrand";
        Brand newBrand = new Brand(brandName, "New Brand Description");
        when(iBrandPersistence.getBrandByName(brandName)).thenReturn(null);

        // Act
        brandUseCase.saveBrand(newBrand);

        // Assert
        verify(iBrandPersistence, times(1)).getBrandByName(brandName);
        verify(iBrandPersistence, times(1)).saveBrand(newBrand);
    }


    @Test
    void saveBrand_WhenBrandExist_ShouldReturnThrowsException() {
        // Arrange
        String brandName = "ExistingBrand";
        Brand existingBrand = new Brand(brandName, "Existing Brand Description");
        when(iBrandPersistence.getBrandByName(brandName)).thenReturn(existingBrand);

        Brand newBrand = new Brand(brandName, "New Brand Description");

        // Act & Assert
        assertThrows(BrandNameAlreadyExistsException.class, () -> {
            brandUseCase.saveBrand(newBrand);
        });

        // Verifications
        verify(iBrandPersistence, times(1)).getBrandByName(brandName);
        verify(iBrandPersistence, never()).saveBrand(any(Brand.class));
    }




    @Test
    void getBrandByName_WhenBrandExist_ShouldGettingBrandSuccessfully(){
        // Arrange
        String brandName = "BrandTest";
        Brand expectedBrand = new Brand(brandName, "Brand Description");
        when(iBrandPersistence.getBrandByName(brandName)).thenReturn(expectedBrand);

        // Act
        Brand actualBrand = brandUseCase.getBrandByName(brandName);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(iBrandPersistence, times(1)).getBrandByName(brandName);

    }


    @Test
    void getBrandByName_WhenBrandDoesNotExist_ShouldReturnNull() {
        // Arrange
        String brandName = "NonExistentBrand";
        when(iBrandPersistence.getBrandByName(brandName)).thenReturn(null);

        // Act
        Brand actualBrand = brandUseCase.getBrandByName(brandName);

        // Assert
        assertNull(actualBrand);
        verify(iBrandPersistence, times(1)).getBrandByName(brandName);
    }




    @Test
    void getAllBrandsSuccessfully(){
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "name";
        boolean ascending = true;

        Brand brand1 = new Brand("Brand 1", "Description 1");
        Brand brand2 = new Brand("Brand 2", "Description 2");
        List<Brand> brands = List.of(brand1, brand2);

        PageResult<Brand> expectedPageResult = new PageResult<>(brands,page,size,10);

        when(iBrandPersistence.getAllBrands(page, size, sortBy, ascending))
                .thenReturn(expectedPageResult);

        // Act
        PageResult<Brand> actualPageResult = brandUseCase.getAllBrands(page, size, sortBy, ascending);

        // Assert
        assertThat(actualPageResult).isEqualTo(expectedPageResult);
        verify(iBrandPersistence, times(1)).getAllBrands(page, size, sortBy, ascending);
    }
}