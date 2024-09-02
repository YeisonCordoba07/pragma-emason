package com.pragma.emason.domain.usecase;

import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.exception.BrandNameAlreadyExistsException;
import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.IBrandPersistence;

public class BrandUseCase implements IBrandService {
    private final IBrandPersistence iBrandPersistence;

    public BrandUseCase(IBrandPersistence iBrandPersistence){
        this.iBrandPersistence = iBrandPersistence;
    }


    @Override
    public void saveBrand(Brand brand) {
        if(this.iBrandPersistence.getBrandByName(brand.getName()) != null){
            throw new BrandNameAlreadyExistsException("A brand with this name already exists.");
        }
        this.iBrandPersistence.saveBrand(brand);
    }


    @Override
    public Brand getBrandByName(String name) {
        return this.iBrandPersistence.getBrandByName(name);
    }


    @Override
    public PageResult<Brand> getAllBrands(int page, int size, String sortBy, boolean ascending) {
        return iBrandPersistence.getAllBrands(page, size, sortBy,ascending);
    }


}
