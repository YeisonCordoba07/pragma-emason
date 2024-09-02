package com.pragma.emason.application.mapper;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemRequestMapper {

    Item toItem(ItemRequestDTO itemRequestDTO);
}
