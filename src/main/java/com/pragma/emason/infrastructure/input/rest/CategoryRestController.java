package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.handler.ICategoryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {

    private final ICategoryHandler iCategoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){
        /*if (categoryRequest.getName().length() > 50) {
            throw new IllegalArgumentException("Category name must not exceed 50 characters");
        }*/
        iCategoryHandler.saveCategoryInDataBase(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
