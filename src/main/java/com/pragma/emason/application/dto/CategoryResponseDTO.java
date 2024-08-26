package com.pragma.emason.application.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryResponseDTO {
    private Integer id;

    private String name;

    private String description;

}
