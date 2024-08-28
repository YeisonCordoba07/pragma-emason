package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.infrastructure.output.jpa.entity.BrandEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistence {
    private final IBrandRepository iBrandRepository;
    private final IBrandEntityMapper iBrandEntityMapper;


    @Override
    public void saveBrand(Brand brand) {
        BrandEntity brandEntity = iBrandEntityMapper.toEntity(brand);
        iBrandRepository.save(brandEntity);
    }

    @Override
    public Brand getBrandByName(String name) {
        return iBrandEntityMapper.toBrand(iBrandRepository.findBrandEntityByName(name));
    }
}
