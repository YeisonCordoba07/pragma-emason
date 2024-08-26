package com.pragma.emason.infrastructure.output.jpa.mapper;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {

    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
}
