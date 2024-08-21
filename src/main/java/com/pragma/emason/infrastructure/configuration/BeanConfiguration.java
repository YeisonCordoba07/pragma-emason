package com.pragma.emason.infrastructure.configuration;

import com.pragma.emason.domain.spi.ICategoryRepository;
import com.pragma.emason.domain.api.ICategoryService;
import com.pragma.emason.domain.usecase.CategoryUseCase;
import com.pragma.emason.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.emason.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final com.pragma.emason.infrastructure.output.jpa.repository.ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;


    @Bean
    public ICategoryRepository categoryRepository(){
        return new CategoryJpaAdapter(iCategoryRepository, iCategoryEntityMapper);
    }
    @Bean
    public ICategoryService categoryService(){
        return new CategoryUseCase(categoryRepository());
    }

}
