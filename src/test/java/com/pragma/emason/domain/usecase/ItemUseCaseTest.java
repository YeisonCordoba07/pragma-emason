package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.model.PageResult;
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

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
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

        List<Category> categories = new ArrayList<>();
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

    @Test
    void getAllItems() {
        int page = 0;
        int size = 2;
        String sortBy = "name";
        String table = "category";
        boolean ascending = true;

        Category category1 = new Category("Electronics", "Electronics Description");
        Category category2 = new Category("Home Appliances", "Home Appliances Description");
        List<Category> categories = List.of(category1, category2);
        Item item1 = new Item("Smartphone", "Smartphone Description", 10, 699.99, new Brand("BrandA", "BrandA Description"), categories);
        Item item2 = new Item("Smartwatch", "Smartwatch Description", 10, 399.99, new Brand("BrandB", "BrandB Description"), categories);
        List<Item> itemList = List.of(item1, item2);
        PageResult<Item> expectedPageResult = new PageResult<>(itemList,page,size,2);
        when(iItemPersistence.getAllItems(page, size, sortBy, table, ascending)).thenReturn(expectedPageResult);


        PageResult<Item> actualPageResult = itemUseCase.getAllItems(page, size, sortBy, table, ascending);

        assertThat(actualPageResult).isEqualTo(expectedPageResult);

        verify(iItemPersistence, times(1)).getAllItems(page, size, sortBy, table, ascending);
    }
}