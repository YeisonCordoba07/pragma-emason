package com.pragma.emason.infrastructure.configuration;

import com.pragma.emason.application.handler.IBrandHandler;
import com.pragma.emason.domain.api.IBrandService;
import com.pragma.emason.domain.api.IItemService;
import com.pragma.emason.domain.spi.IBrandPersistence;
import com.pragma.emason.domain.spi.ICategoryPersistence;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.spi.IItemPersistence;
import com.pragma.emason.domain.usecase.BrandUseCase;
import com.pragma.emason.domain.usecase.CategoryUseCase;
import com.pragma.emason.domain.usecase.ItemUseCase;
import com.pragma.emason.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.pragma.emason.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.emason.infrastructure.output.jpa.adapter.ItemJpaAdapter;
import com.pragma.emason.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.mapper.IItemEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.repository.IBrandRepository;
import com.pragma.emason.infrastructure.output.jpa.repository.IItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final com.pragma.emason.infrastructure.output.jpa.repository.ICategoryRepository iCategoryRepository;

    private final ICategoryEntityMapper iCategoryEntityMapper;

    private final IBrandRepository iBrandRepository;
    private final IBrandEntityMapper iBrandEntityMapper;

    private final IItemRepository iItemRepository;
    private final IItemEntityMapper iItemEntityMapper;



    @Bean
    public ICategoryPersistence categoryRepository(){
        return new CategoryJpaAdapter(iCategoryRepository, iCategoryEntityMapper);
    }
    @Bean
    public ICategoryService categoryService(){
        return new CategoryUseCase(categoryRepository());
    }




    @Bean
    public IBrandPersistence iBrandPersistence(){
        return new BrandJpaAdapter(iBrandRepository, iBrandEntityMapper);
    }

    @Bean
    public IBrandService iBrandService(){
        return new BrandUseCase(iBrandPersistence());
    }




    @Bean
    public IItemPersistence iItemPersistence(){
        return new ItemJpaAdapter(iItemRepository, iItemEntityMapper);
    }

    @Bean
    public IItemService iItemService(){
        // Modifica aquí para pasar también la implementación de ICategoryPersistence
        return new ItemUseCase(iItemPersistence(), categoryRepository(), iBrandPersistence());
    }






}