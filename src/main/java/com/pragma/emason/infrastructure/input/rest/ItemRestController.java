package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.application.handler.IItemHandler;
import com.pragma.emason.application.handler.feign.SupplyFeignClient;
import com.pragma.emason.domain.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Validated
public class ItemRestController {
    private final IItemHandler iItemHandler;
    //private final SupplyFeignClient supplyFeignClient;


    @Operation(summary = "Save a new item", description = "This endpoint allows saving a new item in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Item created successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict: Item already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO){

        iItemHandler.saveItemInDataBase(itemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }






    @GetMapping("/getAll")
    @Operation(summary = "Retrieve all items with pagination, sorting, and filtering",
            description = "Fetches a paginated list of items from the database. The results can be sorted by a specific field, filtered by table, and ordered in ascending or descending order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of items",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageResult.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<PageResult<ItemResponseDTO>> getAllItems(
            @Parameter(description = "Page number for pagination (zero-based index)", example = "0")
            @RequestParam int page,

            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam int size,

            @Parameter(description = "Field to sort the items by", example = "name")
            @RequestParam String sortBy,

            @Parameter(description = "Table to apply the sort on", schema = @Schema(type = "string", allowableValues = {"item", "category", "brand"}))
            @RequestParam String table,

            @Parameter(description = "True for ascending order, false for descending order", example = "true")
            @RequestParam boolean ascending)
    {
        return ResponseEntity.ok(iItemHandler.getAllItems(page, size, sortBy, table, ascending));
    }


    @PatchMapping("/{id}/{increase}")
    public ResponseEntity<Void> patchCategory(
            @PathVariable Integer id,
            @PathVariable Integer increase) {

        // Llamar al servicio para actualizar la categoría de manera parcial
        iItemHandler.increaseItem(id, increase);

        // Devolver la categoría actualizada
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
