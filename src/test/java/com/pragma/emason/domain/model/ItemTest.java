package com.pragma.emason.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ItemTest {

    private Item item;
    private Brand brand;
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        categories = new ArrayList<>();
        categories.add(new Category("Electronics", "Electronics Description"));
        categories.add(new Category("Home Appliances", "Home Appliances Description"));

        brand = new Brand("BrandA", "BrandA Description");

        item = new Item("Smartphone", "Smartphone Description", 10, 699.99, brand, categories);
    }

    @Test
    void testItemConstructorAndGetters() {
        // Assert
        assertEquals("Smartphone", item.getName());
        assertEquals("Smartphone Description", item.getDescription());
        assertEquals(10, item.getQuantity());
        assertEquals(699.99, item.getPrice());
        assertEquals(brand, item.getBrand());
        assertEquals(categories, item.getCategories());
    }


    @Test
    void testSetId() {
        // Act
        item.setId(1);

        // Assert
        assertEquals(1, item.getId());
    }


    @Test
    void testSetName() {
        // Act
        item.setName("Laptop");

        // Assert
        assertEquals("Laptop", item.getName());
    }


    @Test
    void testSetDescription() {
        // Act
        item.setDescription("Laptop Description");

        // Assert
        assertEquals("Laptop Description", item.getDescription());
    }


    @Test
    void testSetQuantity() {
        // Act
        item.setQuantity(20);

        // Assert
        assertEquals(20, item.getQuantity());
    }


    @Test
    void testSetPrice() {
        // Act
        item.setPrice(999.99);

        // Assert
        assertEquals(999.99, item.getPrice());
    }


    @Test
    void testSetBrand() {
        // Arrange
        Brand newBrand = new Brand("BrandB", "BrandB Description");

        // Act
        item.setBrand(newBrand);

        // Assert
        assertEquals(newBrand, item.getBrand());
    }


    @Test
    void testSetCategories() {
        // Arrange
        List<Category> newCategories = new ArrayList<>();
        newCategories.add(new Category("Computers", "Computers Description"));

        // Act
        item.setCategories(newCategories);

        // Assert
        assertEquals(1, item.getCategories().size());
        assertEquals("Computers", item.getCategories().iterator().next().getName());
    }

    @Test
    void testDefaultConstructor() {
        // Act
        Item newItem = new Item();

        // Assert
        assertNotNull(newItem);
    }
}