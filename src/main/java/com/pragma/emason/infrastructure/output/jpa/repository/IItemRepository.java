package com.pragma.emason.infrastructure.output.jpa.repository;

import com.pragma.emason.infrastructure.output.jpa.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface IItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query("SELECT DISTINCT  i FROM ItemEntity i " +
            "JOIN i.brand b " +
            "JOIN i.categories c " +
            "ORDER BY " +
            "CASE " +
                "WHEN :table = 'brand' THEN b.name " +
                "WHEN :table = 'category' THEN c.name " +
                "WHEN :table = 'item' THEN i.name " +
            "END")
    Page<ItemEntity> findAllItems(@Param("table") String table, Pageable pageable);


}

