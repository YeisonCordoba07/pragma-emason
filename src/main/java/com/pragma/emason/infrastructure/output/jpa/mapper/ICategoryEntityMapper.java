package com.pragma.emason.infrastructure.output.jpa.mapper;

import com.pragma.emason.domain.model.Category;
import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface ICategoryEntityMapper {

    @Mapping(target = "id", ignore = true)
    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);

}