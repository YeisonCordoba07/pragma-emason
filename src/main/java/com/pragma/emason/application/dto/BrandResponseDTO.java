package com.pragma.emason.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BrandResponseDTO {

    private Integer id;
    private String name;
    private String description;
}
