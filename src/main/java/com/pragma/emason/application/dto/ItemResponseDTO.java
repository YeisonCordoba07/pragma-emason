package com.pragma.emason.application.dto;

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
    private Integer brandId;
    private Set<Category> categories;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Integer id,
                           String name,
                           String description,
                           Integer quantity,
                           Double price,
                            Integer brandId,
                           Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
        this.categories = categories;
    }


}
