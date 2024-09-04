package com.pragma.emason.domain.usecase;

import org.junit.jupiter.api.Test;

import com.pragma.emason.domain.exception.BrandNotFoundException;
import com.pragma.emason.domain.exception.CategoryNotFoundException;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.spi.IItemPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ItemUseCaseTest {

    @InjectMocks
    private ItemUseCase itemUseCase;

    @Mock
    private IItemPersistence iItemPersistence;

    @Mock
    private ICategoryPersistence iCategoryPersistence;

    @Mock
    private IBrandPersistence iBrandPersistence;


    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Electronics", "Electronics Description"));
        categories.add(new Category("Home Appliances", "Home Appliances Description"));

        Brand brand = new Brand("BrandA", "BrandA Description");

        item = new Item(
                "Smartphone",
                "Smartphone Description",
                10,
                699.99,
                brand,
                categories);
    }


    @Test
    void saveItem_ShouldSaveItem_WhenAllCategoriesAndBrandExist() {
        // Arrange
        when(iCategoryPersistence.getCategoryByName("Electronics"))
                .thenReturn(new Category("Electronics", "Electronics Description"));

        when(iCategoryPersistence.getCategoryByName("Home Appliances"))
                .thenReturn(new Category("Home Appliances", "Home Appliances Description"));


        when(iBrandPersistence.getBrandByName("BrandA"))
                .thenReturn(new Brand("BrandA", "BrandA Description"));

        // Act
        itemUseCase.saveItem(item);

        // Assert
        verify(iCategoryPersistence, times(2)).getCategoryByName(anyString());
        verify(iBrandPersistence).getBrandByName("BrandA");
        verify(iItemPersistence).saveItem(item);
    }


    @Test
    void saveItem_ShouldThrowCategoryNotFoundException_WhenCategoryDoesNotExist() {
        // Arrange
        when(iCategoryPersistence.getCategoryByName("Electronics"))
                .thenReturn(null);

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> itemUseCase.saveItem(item));
        verify(iItemPersistence, never()).saveItem(any(Item.class));
    }


    @Test
    void saveItem_ShouldThrowBrandNotFoundException_WhenBrandDoesNotExist() {
        // Arrange
        when(iCategoryPersistence.getCategoryByName(anyString()))
                .thenReturn(new Category("Electronics", "Electronics Description"));

        when(iBrandPersistence.getBrandByName("BrandA"))
                .thenReturn(null);

        // Act & Assert
        assertThrows(BrandNotFoundException.class, () -> itemUseCase.saveItem(item));
        verify(iItemPersistence, never()).saveItem(any(Item.class));
    }
}