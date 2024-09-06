package com.pragma.emason.application.mapper;

import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemResponseMapper {
    ItemResponseDTO toResponse(Item item);

    List<ItemResponseDTO> toResponseList(List<Item> itemList);
}
