package com.pragma.emason.application.handler;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.mapper.IBrandRequestMapper;
import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.model.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class BrandHandler implements IBrandHandler{
    private final IBrandService iBrandService;
    private final IBrandRequestMapper iBrandRequestMapper;

    public BrandHandler(
            IBrandService iBrandService,
            IBrandRequestMapper iBrandRequestMapper) {
        this.iBrandService = iBrandService;
        this.iBrandRequestMapper = iBrandRequestMapper;
    }

    @Override
    public void saveBrandInDataBase(BrandRequestDTO brandRequestDTO) {
        Brand brand = this.iBrandRequestMapper.toBrand(brandRequestDTO);
        this.iBrandService.saveBrand(brand);
    }
}
