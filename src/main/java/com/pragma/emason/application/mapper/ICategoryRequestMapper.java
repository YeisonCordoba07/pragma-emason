package com.pragma.emason.application.mapper;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    //@Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequestDTO categoryRequestDTO);

}