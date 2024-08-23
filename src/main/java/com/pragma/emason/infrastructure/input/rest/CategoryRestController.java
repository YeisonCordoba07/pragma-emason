package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.handler.ICategoryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {

    private final ICategoryHandler iCategoryHandler;


    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){

        iCategoryHandler.saveCategoryInDataBase(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(iCategoryHandler.getCategoryByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponseDTO>> getCategories(){
        return ResponseEntity.ok(iCategoryHandler.getAllCategories());
    }
}
