package com.pragma.emason.infrastructure.configuration;

import com.pragma.emason.domain.spi.CategoryRepository;
import com.pragma.emason.domain.api.CategoryService;
import com.pragma.emason.domain.usecase.CategoryUseCase;
import com.pragma.emason.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.emason.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.pragma.emason.infrastructure.output.jpa.respository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository iCategoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;


    @Bean
    public CategoryRepository categoryRepository(){
        return new CategoryJpaAdapter(iCategoryRepository, categoryEntityMapper);
    }
    @Bean
    public CategoryService categoryService(){
        return new CategoryUseCase(categoryRepository());
    }

}
