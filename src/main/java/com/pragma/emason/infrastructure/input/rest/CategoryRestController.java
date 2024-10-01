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

import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_200;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_201;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_400;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_404;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_500;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_SAVE_CATEGORY;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_SAVE_CATEGORY;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_201_DESCRIPTION_SAVE_CATEGORY;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_GET_CATEGORY_BY_NAME;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_GET_CATEGORY_BY_NAME;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_200_DESCRIPTION_GET_CATEGORY_BY_NAME;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_404_DESCRIPTION_CATEGORY;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_GET_ALL_CATEGORIES;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_GET_ALL_CATEGORIES;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_200_DESCRIPTION_GET_ALL_CATEGORIES;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_400_DESCRIPTION;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_500_DESCRIPTION;



@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {

    private final ICategoryHandler iCategoryHandler;



    @Operation(summary = OPERATION_SUMMARY_SAVE_CATEGORY, description = OPERATION_DESCRIPTION_SAVE_CATEGORY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_201, description = RESPONSE_201_DESCRIPTION_SAVE_CATEGORY),
            @ApiResponse(responseCode = STATUS_400, description = RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = STATUS_500, description = RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){

        iCategoryHandler.saveCategoryInDataBase(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @Operation(summary = OPERATION_SUMMARY_GET_CATEGORY_BY_NAME, description = OPERATION_DESCRIPTION_GET_CATEGORY_BY_NAME)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_200, description = RESPONSE_200_DESCRIPTION_GET_CATEGORY_BY_NAME,
                    content = @Content(schema = @Schema(implementation = CategoryResponseDTO.class))),
            @ApiResponse(responseCode = STATUS_404, description = RESPONSE_404_DESCRIPTION_CATEGORY,
                    content = @Content),
            @ApiResponse(responseCode = STATUS_500, description = RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(iCategoryHandler.getCategoryByName(name));
    }



    @Operation(summary = OPERATION_SUMMARY_GET_ALL_CATEGORIES, description = OPERATION_DESCRIPTION_GET_ALL_CATEGORIES)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_200, description = RESPONSE_200_DESCRIPTION_GET_ALL_CATEGORIES,
                    content = @Content(schema = @Schema(implementation = PageResult.class))),
            @ApiResponse(responseCode = STATUS_400, description = RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = STATUS_500, description = RESPONSE_500_DESCRIPTION,
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