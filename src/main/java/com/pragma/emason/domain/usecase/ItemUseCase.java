package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.spi.IItemPersistence;

import java.util.Optional;

public class ItemUseCase implements IItemService {

    private final IItemPersistence iItemPersistence;
    private final ICategoryPersistence iCategoryPersistence;

    public ItemUseCase(IItemPersistence iItemPersistence, ICategoryPersistence iCategoryPersistence) {
        this.iItemPersistence = iItemPersistence;
        this.iCategoryPersistence = iCategoryPersistence;
    }

    @Override
    public void saveItem(Item item) {

        // Recorrer el conjunto de categorías en el Item
        for (Category category : item.getCategories()) {
            // Verificar si la categoría existe en la base de datos
            Optional<Category> existingCategory = Optional.ofNullable(
                    iCategoryPersistence.getCategoryByName(category.getName()));

            if (existingCategory.isPresent() && existingCategory.get().getId().equals(category.getId())) {
                // Si la categoría existe, se asocia con el Item
                category = existingCategory.get();
            } else {
                // Si la categoría no existe, puedes lanzar una excepción o manejarlo según sea necesario
                throw new RuntimeException("La categoría " + category.getName() + " no existe en la base de datos o su id no se corresponde con el nombre usado.");
            }
        }

        iItemPersistence.saveItem(item);

    }
}
