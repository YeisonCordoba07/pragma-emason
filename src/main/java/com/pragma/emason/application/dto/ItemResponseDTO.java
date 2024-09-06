package com.pragma.emason.application.dto;

import com.pragma.emason.domain.model.Brand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class ItemResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Brand brand;
    private List<CategoryItemResponseDTO> categories;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Integer id,
                           String name,
                           String description,
                           Integer quantity,
                           Double price,
                           Brand brand,
                           List<CategoryItemResponseDTO> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }


}
