package com.pragma.emason.application.mapper;


import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {

    BrandResponseDTO toResponse(Brand brand);
}

