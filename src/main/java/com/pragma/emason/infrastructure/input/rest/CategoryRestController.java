package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.CategoryRequest;
import com.pragma.emason.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler iCategoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequest categoryRequest){
        iCategoryHandler.saveCategoryInDataBase(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
