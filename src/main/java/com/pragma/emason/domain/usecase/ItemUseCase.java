package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.IItemPersistence;

public class ItemUseCase implements IItemService {

    private final IItemPersistence iItemPersistence;

    public ItemUseCase(IItemPersistence iItemPersistence) {
        this.iItemPersistence = iItemPersistence;
    }

    @Override
    public void saveItem(Item item) {
        iItemPersistence.saveItem(item);
    }
}
