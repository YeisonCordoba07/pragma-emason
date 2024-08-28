package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.application.handler.IBrandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Validated
public class BrandRestController {
    private final IBrandHandler iBrandHandler;


    @Operation(summary = "Create a new brand",
            description = "Saves a new brand in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandRequestDTO brandRequestDTO){
        iBrandHandler.saveBrandInDataBase(brandRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Get brand by name",
            description = "Fetches a brand's details by its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Brand found",
                    content = @Content(schema = @Schema(implementation = BrandResponseDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Brand not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{name}")
    public ResponseEntity<BrandResponseDTO> getBrandByName(
            @PathVariable @Schema(
                    description = "The name of the brand to fetch",
                    example = "BrandTest")
            String name) {
        BrandResponseDTO brandResponse = iBrandHandler.getBrandByName(name);
        return ResponseEntity.ok(brandResponse);
    }
}
