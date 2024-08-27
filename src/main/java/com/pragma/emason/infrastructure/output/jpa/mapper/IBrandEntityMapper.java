package com.pragma.emason.infrastructure.output.jpa.mapper;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IBrandEntityMapper {

    @Mapping(target = "id", ignore = true)
    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);
}
