package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.ItemRequestDTO;

public interface IItemHandler {
    void saveItemInDataBase(ItemRequestDTO itemRequestDTO);

}
