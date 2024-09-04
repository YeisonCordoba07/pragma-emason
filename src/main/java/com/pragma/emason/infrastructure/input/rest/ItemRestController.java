package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.dto.ItemResponseDTO;
import com.pragma.emason.application.handler.IItemHandler;
import com.pragma.emason.domain.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.Getter;
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
    public ResponseEntity<PageResult<ItemResponseDTO>> getAllItems(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String table,
            @RequestParam boolean ascending){
        return ResponseEntity.ok(iItemHandler.getAllItems(page, size, sortBy, table, ascending));
    }
}
