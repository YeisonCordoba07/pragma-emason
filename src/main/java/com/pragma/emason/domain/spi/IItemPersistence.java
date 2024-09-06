package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.model.PageResult;

public interface IItemPersistence {
    void saveItem(Item item);

    PageResult<Item> getAllItems(int page, int size, String sortBy, String table, boolean ascending);
}
