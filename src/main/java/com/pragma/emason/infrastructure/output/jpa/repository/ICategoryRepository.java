package com.pragma.emason.infrastructure.output.jpa.repository;

import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findCategoryEntityByName(String name);

}