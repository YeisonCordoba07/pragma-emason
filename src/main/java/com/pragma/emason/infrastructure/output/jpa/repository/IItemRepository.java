package com.pragma.emason.infrastructure.output.jpa.repository;

import com.pragma.emason.infrastructure.output.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface IItemRepository extends JpaRepository<ItemEntity, Integer> {
}
