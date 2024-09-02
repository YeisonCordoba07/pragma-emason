package com.pragma.emason.application.dto;

import com.pragma.emason.domain.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ItemRequestDTO {

    private Integer id;

    @NotNull
    @NotBlank(message = "Name cannot be null blank")
    private String name;

    @NotNull
    @NotBlank(message = "Description cannot be null blank")
    private String description;

    @Min(0)
    @NotNull
    private Integer quantity;

    @Min(0)
    @NotNull
    private Double price;

    @NotNull
    private Integer brandId;

    @NotNull
    @Size.List({
        @Size(min = 1, message = "At least one category is required"),
        @Size(max = 5, message = "At most five categories are allowed")
    })
    private Set<Category> categories;

}
