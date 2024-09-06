package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.domain.model.PageResult;

public interface IItemHandler {
    void saveItemInDataBase(ItemRequestDTO itemRequestDTO);

    PageResult<ItemResponseDTO> getAllItems(int page, int size, String sortBy, String table, boolean ascending);


}
