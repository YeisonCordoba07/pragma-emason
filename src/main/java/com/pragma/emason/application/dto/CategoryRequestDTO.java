package com.pragma.emason.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CategoryRequestDTO {
    private Integer id;

    @NotNull
    @NotBlank(message = "Name cannot be null blank")
    @Size(max = 50, message = "Name must be less than or equal to 50 characters")
    private String name;

    @NotNull
    @NotBlank(message = "Name cannot be null blank")
    @Size(max = 90, message = "Description must be less than or equal to 50 characters")
    private String description;

}
