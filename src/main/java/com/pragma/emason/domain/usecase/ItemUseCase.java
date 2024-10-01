package com.pragma.emason.domain.usecase;

import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.spi.IItemPersistence;
import com.pragma.emason.domain.exception.BrandNotFoundException;
import com.pragma.emason.domain.exception.CategoryNotFoundException;
import com.pragma.emason.domain.utils.FinalContants;

import java.util.ArrayList;
import java.util.List;


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

        List<Category> categories = new ArrayList<>();

        for (Category categoryName : item.getCategories()) {
            Category existingCategory = iCategoryPersistence.getCategoryByName(categoryName.getName());

            if (existingCategory == null) {
                throw new CategoryNotFoundException(
                        String.format(FinalContants.CATEGORY_NOT_FOUND, categoryName.getName()));
            }
            categories.add(existingCategory);
        }

        Brand existingBrand = iBrandPersistence.getBrandByName(item.getBrand().getName());

        if(existingBrand == null){
            throw new BrandNotFoundException(
                    String.format(FinalContants.BRAND_NOT_FOUND, item.getBrand().getName()));
        }


        item.setBrand(existingBrand);
        item.setCategories(categories);
        iItemPersistence.saveItem(item);
    }


    @Override
    public PageResult<Item> getAllItems(int page, int size, String sortBy, String table, boolean ascending) {

        return iItemPersistence.getAllItems(page, size, sortBy, table, ascending);
    }

    @Override
    public Item getItemById(Integer id) {
        return iItemPersistence.getItemById(id);
    }

    @Override
    public void increaseItem(Integer id, Integer increase) {

        Item item = getItemById(id);
        item.setQuantity(item.getQuantity() + increase);
        iItemPersistence.saveItem(item);

    }
}
