package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Item;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.IItemPersistence;
import com.pragma.emason.infrastructure.output.jpa.entity.ItemEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.IItemEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;


@RequiredArgsConstructor
public class ItemJpaAdapter implements IItemPersistence {
    private final IItemRepository iItemRepository;
    private final IItemEntityMapper iItemEntityMapper;



    @Override
    public void saveItem(Item item) {
        ItemEntity itemEntity = iItemEntityMapper.toEntity(item);
        iItemRepository.save(itemEntity);
    }



    @Override
    public PageResult<Item> getAllItems(int page, int size, String sortBy, String table, boolean ascending) {
        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        // Sort is based on the "table" parameter (which determines the field to order by)


        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Call the repository method to get the sorted results dynamically based on table
        Page<ItemEntity> pageList = iItemRepository.findAllItems(table, pageable);

        return new PageResult<>(
                pageList.getContent().stream()
                        .map(iItemEntityMapper::toItem)
                        .toList(),
                pageList.getNumber(),
                pageList.getSize(),
                pageList.getTotalElements()
        );
    }



    @Override
    public Item getItemById(Integer id) {

        Optional<ItemEntity> itemEntity = iItemRepository.findById(id);
        return itemEntity.map(iItemEntityMapper::toItem).orElse(null);
    }
}
