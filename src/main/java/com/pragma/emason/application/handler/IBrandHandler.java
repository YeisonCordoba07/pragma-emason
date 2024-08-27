package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;

public interface IBrandHandler {
    void saveBrandInDataBase(BrandRequestDTO brandRequestDTO);
}
