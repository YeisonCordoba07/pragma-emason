package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.spi.IBrandPersistence;

public class BrandUseCase implements IBrandService {
    private final IBrandPersistence iBrandPersistence;

    public BrandUseCase(IBrandPersistence iBrandPersistence){
        this.iBrandPersistence = iBrandPersistence;
    }

    @Override
    public void saveBrand(Brand brand) {
        /*if(this.iBrandPersistence.getBrandByName(brand.getName()) != null){
            BrandNameAlreadyExists("A brand with this name already exists.");
        }*/
        iBrandPersistence.saveBrand(brand);
    }
}
