package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.spi.IItemPersistence;
import com.pragma.emason.infrastructure.output.jpa.entity.ItemEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.IItemEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemJpaAdapter implements IItemPersistence {
    private final IItemRepository iItemRepository;
    private final IItemEntityMapper iItemEntityMapper;

    @Override
    public void saveItem(Item item) {
        ItemEntity itemEntity = iItemEntityMapper.toEntity(item);
        iItemRepository.save(itemEntity);
    }
}
