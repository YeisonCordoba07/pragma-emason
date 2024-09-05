package com.pragma.emason.application.mapper;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.Category;
import com.pragma.emason.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemRequestMapper {


    default Item toItemSet(ItemRequestDTO itemRequestDTO) {
        List<Category> categories = itemRequestDTO.getCategories().stream()
                .map(name -> {
                    Category category = new Category();
                    category.setName(name);
                    return category;
                })
                .collect(Collectors.toList());

        Brand brand = new Brand();
        brand.setName(itemRequestDTO.getBrandName());

        return new Item(
                itemRequestDTO.getName(),
                itemRequestDTO.getDescription(),
                itemRequestDTO.getQuantity(),
                itemRequestDTO.getPrice(),
                brand,
                categories
        );
    }
    
}
