package com.pragma.emason.application.mapper;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemRequestMapper {

    //Item toItem(ItemRequestDTO itemRequestDTO);


    default Item toCategorySet(ItemRequestDTO itemRequestDTO) {
        Set<Category> categories = itemRequestDTO.getCategories().stream()
                .map(name -> {
                    Category category = new Category();
                    category.setName(name); // Asumiendo que Category tiene un atributo name
                    return category;
                })
                .collect(Collectors.toSet());

        return new Item(
                itemRequestDTO.getName(),
                itemRequestDTO.getDescription(),
                itemRequestDTO.getQuantity(),
                itemRequestDTO.getPrice(),
                itemRequestDTO.getBrandId(),
                categories
        );
    }
}
