package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.spi.IBrandPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void testSaveBrand_ShouldSaveBrand() {
        // Arrange
        Brand brand = new Brand();
        brand.setName("Brand's name");
        brand.setDescription("Brand's description");

        // Act
        brandUseCase.saveBrand(brand);

        // Assert
        verify(iBrandPersistence, times(1)).saveBrand(brand);
    }
}