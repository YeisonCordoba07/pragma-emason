package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.BrandRequestDTO;
import com.pragma.emason.application.dto.BrandResponseDTO;
import com.pragma.emason.application.handler.IBrandHandler;
import com.pragma.emason.domain.model.PageResult;
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


import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_200;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_201;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_400;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_404;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_500;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_CREATE_BRAND;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_CREATE_BRAND;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_201_DESCRIPTION_CREATE_BRAND;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_400_DESCRIPTION;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_GET_BRAND_BY_NAME;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_GET_BRAND_BY_NAME;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_200_DESCRIPTION_GET_BRAND_BY_NAME;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_404_DESCRIPTION_BRRAND;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_GET_ALL_BRANDS;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_GET_ALL_BRANDS;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_200_DESCRIPTION_GET_ALL_BRANDS;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_500_DESCRIPTION;




@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Validated
public class BrandRestController {
    private final IBrandHandler iBrandHandler;


    @Operation(summary = OPERATION_SUMMARY_CREATE_BRAND, description = OPERATION_DESCRIPTION_CREATE_BRAND)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_201, description = RESPONSE_201_DESCRIPTION_CREATE_BRAND, content = @Content),
            @ApiResponse(responseCode = STATUS_400, description = RESPONSE_400_DESCRIPTION, content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandRequestDTO brandRequestDTO){
        iBrandHandler.saveBrandInDataBase(brandRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @Operation(summary = OPERATION_SUMMARY_GET_BRAND_BY_NAME, description = OPERATION_DESCRIPTION_GET_BRAND_BY_NAME)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_200, description = RESPONSE_200_DESCRIPTION_GET_BRAND_BY_NAME, content = @Content(schema = @Schema(implementation = BrandResponseDTO.class))),
            @ApiResponse(responseCode = STATUS_404, description = RESPONSE_404_DESCRIPTION_BRRAND, content = @Content(schema = @Schema(implementation = String.class)))
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



    @Operation(summary = OPERATION_SUMMARY_GET_ALL_BRANDS, description = OPERATION_DESCRIPTION_GET_ALL_BRANDS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_200, description = RESPONSE_200_DESCRIPTION_GET_ALL_BRANDS),
            @ApiResponse(responseCode = STATUS_400, description = RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = STATUS_500, description = RESPONSE_500_DESCRIPTION)
    })
    @GetMapping("/getAll")
    public ResponseEntity<PageResult<BrandResponseDTO>> getAllBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        PageResult<BrandResponseDTO> result = iBrandHandler.getAllBrands(page, size, sortBy, ascending);

        if (page >= result.getTotalPages()) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(result);
    }
}
