package com.pragma.emason.application.mapper;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

    CategoryResponseDTO toResponse(Category category);

}
