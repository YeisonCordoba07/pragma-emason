package com.pragma.emason.infrastructure.output.jpa.repository;

import com.pragma.emason.infrastructure.output.jpa.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface IItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query(
            "SELECT i FROM ItemEntity i " +
                    "JOIN i.categories c " +
                    "JOIN i.brand b " +
                    "ORDER BY " +
                    "CASE " +
                        "WHEN :table = 'category' THEN c.name " +
                        "WHEN :table = 'brand' THEN b.name " +
                        "ELSE i.name " +
                    "END"
    )
    Page<ItemEntity> findAllItems(@Param("table") String table, Pageable pageable);


}

