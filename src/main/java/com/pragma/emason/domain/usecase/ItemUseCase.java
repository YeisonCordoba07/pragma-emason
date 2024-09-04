package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.spi.IItemPersistence;
import com.pragma.emason.domain.exception.BrandNotFoundException;
import com.pragma.emason.domain.exception.CategoryNotFoundException;

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
                throw new CategoryNotFoundException("The Category " + categoryName.getName() + " not exists in database.");
            }

            categories.add(existingCategory);
        }

        Brand existingBrand = iBrandPersistence.getBrandByName(item.getBrand().getName());
        if(existingBrand == null){
            throw new BrandNotFoundException("The Brand " + item.getBrand().getName() + " not exists in database.");
        }

        item.setBrand(existingBrand);
        item.setCategories(categories);
        iItemPersistence.saveItem(item);
    }
}
