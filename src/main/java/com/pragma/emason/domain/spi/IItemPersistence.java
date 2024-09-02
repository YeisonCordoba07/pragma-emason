package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Item;

public interface IItemPersistence {
    void saveItem(Item item);
}
