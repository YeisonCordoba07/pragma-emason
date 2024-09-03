package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.spi.IItemPersistence;

import java.util.HashSet;
import java.util.Set;

public class ItemUseCase implements IItemService {

    private final IItemPersistence iItemPersistence;
    private final ICategoryPersistence iCategoryPersistence;

    public ItemUseCase(IItemPersistence iItemPersistence, ICategoryPersistence iCategoryPersistence) {
        this.iItemPersistence = iItemPersistence;
        this.iCategoryPersistence = iCategoryPersistence;
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

        item.setCategories(categories);
        iItemPersistence.saveItem(item);
    }
}
