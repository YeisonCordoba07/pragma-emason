package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.spi.IItemPersistence;

import java.util.HashSet;
import java.util.Set;

public class ItemUseCase implements IItemService {

    private final IItemPersistence iItemPersistence;
    private final ICategoryPersistence iCategoryPersistence;
    private final IBrandPersistence iBrandPersistence;

    public ItemUseCase(IItemPersistence iItemPersistence,
                       ICategoryPersistence iCategoryPersistence,
                       IBrandPersistence iBrandPersistence) {
        this.iItemPersistence = iItemPersistence;
        this.iCategoryPersistence = iCategoryPersistence;
        this.iBrandPersistence = iBrandPersistence;
    }

    @Override
    public void saveItem(Item item) {
        Set<Category> categories = new HashSet<>();

        for (Category categoryName : item.getCategories()) {
            Category existingCategory = iCategoryPersistence.getCategoryByName(categoryName.getName());

            if (existingCategory == null) {
                throw new RuntimeException("La categoría " + categoryName.getName() + " no existe en la base de datos.");
            }

            categories.add(existingCategory);
        }

        Brand existingBrand = iBrandPersistence.getBrandByName(item.getBrand().getName());
        if(existingBrand == null){
            throw new RuntimeException("El Brand " + item.getBrand() + " no existe en la base de datos.");
        }

        item.setBrand(existingBrand);
        item.setCategories(categories);
        iItemPersistence.saveItem(item);
    }
}
