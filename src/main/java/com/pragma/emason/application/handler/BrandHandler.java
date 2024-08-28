package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.application.mapper.IBrandRequestMapper;
import com.pragma.emason.application.mapper.IBrandResponseMapper;
import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.model.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class BrandHandler implements IBrandHandler{
    private final IBrandService iBrandService;
    private final IBrandRequestMapper iBrandRequestMapper;
    private final IBrandResponseMapper iBrandResponseMapper;

    public BrandHandler(
            IBrandService iBrandService,
            IBrandRequestMapper iBrandRequestMapper,
            IBrandResponseMapper iBrandResponseMapper) {
        this.iBrandService = iBrandService;
        this.iBrandRequestMapper = iBrandRequestMapper;
        this.iBrandResponseMapper = iBrandResponseMapper;
    }

    @Override
    public void saveBrandInDataBase(BrandRequestDTO brandRequestDTO) {
        Brand brand = this.iBrandRequestMapper.toBrand(brandRequestDTO);
        this.iBrandService.saveBrand(brand);
    }

    @Override
    public BrandResponseDTO getBrandByName(String name) {
        return iBrandResponseMapper.toResponse(iBrandService.getBrandByName(name));
    }
}
