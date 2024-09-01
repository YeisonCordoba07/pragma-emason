package com.pragma.emason.infrastructure.output.jpa.adapter;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.PageResult;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.infrastructure.output.jpa.entity.BrandEntity;
import com.pragma.emason.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public PageResult<Brand> getAllCategories(int page, int size, String sortBy, boolean ascending) {
        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BrandEntity> pageList = iBrandRepository.findAll(pageable);

        return new PageResult<>(
                pageList.getContent().stream()
                        .map(iBrandEntityMapper::toBrand)
                        .toList(),
                pageList.getNumber(),
                pageList.getSize(),
                pageList.getTotalElements()
        );
    }
}
