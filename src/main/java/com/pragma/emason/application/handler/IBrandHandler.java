package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;

public interface IBrandHandler {
    void saveBrandInDataBase(BrandRequestDTO brandRequestDTO);
    BrandResponseDTO getBrandByName(String name);
}
