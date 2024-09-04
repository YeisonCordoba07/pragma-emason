package com.pragma.emason.application.dto;

import com.pragma.emason.domain.model.Brand;
import com.pragma.emason.domain.model.Category;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter

public class ItemResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Brand brand;
    private Set<Category> categories;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Integer id,
                           String name,
                           String description,
                           Integer quantity,
                           Double price,
                           Brand brand,
                           Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }


}
