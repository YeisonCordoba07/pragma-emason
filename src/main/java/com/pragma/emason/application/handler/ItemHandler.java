package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.application.mapper.IItemRequestMapper;
import com.pragma.emason.application.mapper.IItemResponseMapper;
import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemHandler implements IItemHandler {
    private final IItemService iItemService;
    private final IItemRequestMapper iItemRequestMapper;
    private final IItemResponseMapper iItemResponseMapper;



    public ItemHandler(
            IItemService iItemService,
            IItemRequestMapper iItemRequestMapper,
            IItemResponseMapper iItemResponseMapper) {
        this.iItemService = iItemService;
        this.iItemRequestMapper = iItemRequestMapper;
        this.iItemResponseMapper = iItemResponseMapper;
    }


    @Override
    public void saveItemInDataBase(ItemRequestDTO itemRequestDTO) {
        Item item = this.iItemRequestMapper.toItemSet(itemRequestDTO);
        this.iItemService.saveItem(item);
    }

    @Override
    public PageResult<ItemResponseDTO> getAllItems(int page, int size, String sortBy, String table, boolean ascending) {
        PageResult<Item> item = iItemService.getAllItems(page, size, sortBy, table, ascending);
        return new PageResult<>(
                iItemResponseMapper.toResponseList(item.getContent()),
                item.getPage(),
                item.getSize(),
                item.getTotalElements());


    }
}
