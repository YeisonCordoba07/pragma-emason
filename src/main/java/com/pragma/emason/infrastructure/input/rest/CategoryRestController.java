package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.CategoryRequestDTO;
import com.pragma.emason.application.dto.CategoryResponseDTO;
import com.pragma.emason.application.handler.ICategoryHandler;
import com.pragma.emason.domain.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {

    private final ICategoryHandler iCategoryHandler;


    //http://localhost:8090/swagger-ui.html

    @Operation(summary = "Save a new category",
            description = "Creates a new category and saves it to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){

        iCategoryHandler.saveCategoryInDataBase(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @Operation(summary = "Get a category by name",
            description = "Fetches the details of a specific category by its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found",
                    content = @Content(schema = @Schema(implementation = CategoryResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(iCategoryHandler.getCategoryByName(name));
    }



    @Operation(summary = "Get all categories with pagination and sorting",
            description = "Fetches all categories with optional pagination and sorting parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PageResult.class))),
            @ApiResponse(responseCode = "400", description = "Invalid pagination or sorting parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<PageResult<CategoryResponseDTO>> getCategories(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean ascending) {

        return ResponseEntity.ok(iCategoryHandler.getAllCategories(page, size, sortBy, ascending));
    }
}