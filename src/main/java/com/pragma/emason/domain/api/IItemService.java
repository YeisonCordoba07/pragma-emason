package com.pragma.emason.domain.api;


import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.model.PageResult;

public interface IItemService {

    void saveItem(Item item);

    PageResult<Item> getAllItems(int page, int size, String sortBy, String table, boolean ascending);
}
