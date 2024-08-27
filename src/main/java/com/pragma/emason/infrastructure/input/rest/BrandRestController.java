package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.handler.IBrandHandler;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
@Validated
public class BrandRestController {
    private final IBrandHandler iBrandHandler;

    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandRequestDTO brandRequestDTO){
        iBrandHandler.saveBrandInDataBase(brandRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
