package com.pragma.emason.domain.spi;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;

public interface IBrandPersistence {

    void saveBrand(Brand brand);

    Brand getBrandByName(String name);

    PageResult<Brand> getAllCategories(int page, int size, String sortBy, boolean ascending);
}
