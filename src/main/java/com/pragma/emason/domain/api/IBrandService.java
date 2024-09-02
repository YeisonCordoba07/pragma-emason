package com.pragma.emason.domain.api;


import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;

public interface IBrandService {

    void saveBrand(Brand brand);

    Brand getBrandByName(String name);

    PageResult<Brand> getAllBrands(int page, int size, String sortBy, boolean ascending);

}
