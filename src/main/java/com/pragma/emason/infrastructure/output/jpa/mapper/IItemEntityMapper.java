package com.pragma.emason.infrastructure.output.jpa.mapper;

import com.pragma.emason.domain.model.Item;
import com.pragma.emason.infrastructure.output.jpa.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IItemEntityMapper {
    ItemEntity toEntity(Item item);

    Item toItem(ItemEntity itemEntity);
}
