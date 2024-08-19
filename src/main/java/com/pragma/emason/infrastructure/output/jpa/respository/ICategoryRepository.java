package com.pragma.emason.infrastructure.output.jpa.respository;

import com.pragma.emason.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
