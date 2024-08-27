package com.pragma.emason.infrastructure.output.jpa.repository;

import com.pragma.emason.infrastructure.output.jpa.entity.BrandEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<BrandEntity, Integer> {

}
