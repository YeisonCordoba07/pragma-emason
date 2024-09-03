package com.pragma.emason.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ItemRequestDTO {

    @NotNull
    @NotBlank(message = "Name cannot be null blank")
    private String name;

    @NotNull
    @NotBlank(message = "Description cannot be null blank")
    private String description;

    @Min(0)
    @NotNull
    @Positive(message="Quantity must be positive")
    private Integer quantity;

    @Min(0)
    @Positive(message="Price must be positive")
    @NotNull
    private Double price;

    @NotNull
    @NotBlank(message = "Brand's name cannot be null blank")
    private String brandName;

    @NotNull
    @Size(min = 1, message = "At least one category is required")
    @Size(max = 3, message = "At most three categories are allowed")
    private Set<String> categories;

}
