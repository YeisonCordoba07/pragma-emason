package com.pragma.emason.application.mapper;

import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

    CategoryResponseDTO toResponse(Category category);

    default List<CategoryResponseDTO> toResponseList(List<Category> categoryList){
        return categoryList.stream()
                .map(category ->{
                    CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                    categoryResponseDTO.setId(category.getId());
                    categoryResponseDTO.setName(category.getName());
                    categoryResponseDTO.setDescription(category.getDescription());
                    return categoryResponseDTO;
                }).toList();
    }

}
