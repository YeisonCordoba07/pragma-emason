package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.domain.model.PageResult;

public interface IBrandHandler {
    void saveBrandInDataBase(BrandRequestDTO brandRequestDTO);
    BrandResponseDTO getBrandByName(String name);

    PageResult<BrandResponseDTO> getAllCategories(int page, int size, String sortBy, boolean ascending);
}
