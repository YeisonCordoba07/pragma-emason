package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.mapper.IItemRequestMapper;
import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemHandler implements IItemHandler {
    private final IItemService iItemService;
    private final IItemRequestMapper iItemRequestMapper;



    public ItemHandler(
            IItemService iItemService,
            IItemRequestMapper iItemRequestMapper) {
        this.iItemService = iItemService;
        this.iItemRequestMapper = iItemRequestMapper;
    }


    @Override
    public void saveItemInDataBase(ItemRequestDTO itemRequestDTO) {
        Item item = this.iItemRequestMapper.toCategorySet(itemRequestDTO);
        this.iItemService.saveItem(item);
    }
}
