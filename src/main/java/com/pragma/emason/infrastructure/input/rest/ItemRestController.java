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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_200;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_201;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_400;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.STATUS_500;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_CREATE;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_CREATE;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_GET_ALL_ITEMS;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_GET_ALL_ITEMS;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_SUMMARY_INCREASE_ITEM_QUANTITY;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.OPERATION_DESCRIPTION_INCREASE_ITEM_QUANTITY;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_200_DESCRIPTION_GET_ALL_ITEMS;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_201_DESCRIPTION;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_201_DESCRIPTION_INCREASE_ITEM;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_400_DESCRIPTION;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_400_DESCRIPTION_INCREASE_ITEM;
import static com.pragma.emason.infrastructure.util.ApiDocumentationConstants.RESPONSE_500_DESCRIPTION;



@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = {"*"})
public class ItemRestController {

    private final IItemHandler iItemHandler;



    @Operation(summary = OPERATION_SUMMARY_CREATE, description = OPERATION_DESCRIPTION_CREATE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_201,
                    description = RESPONSE_201_DESCRIPTION),
            @ApiResponse(
                    responseCode = STATUS_400,
                    description = RESPONSE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        iItemHandler.saveItemInDataBase(itemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




    @Operation(summary = OPERATION_SUMMARY_GET_ALL_ITEMS, description = OPERATION_DESCRIPTION_GET_ALL_ITEMS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_200, description = RESPONSE_200_DESCRIPTION_GET_ALL_ITEMS,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResult.class))),
            @ApiResponse(responseCode = STATUS_400, description = RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = STATUS_500, description = RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<PageResult<ItemResponseDTO>> getAllItems(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String table,
            @RequestParam boolean ascending) {
        return ResponseEntity.ok(iItemHandler.getAllItems(page, size, sortBy, table, ascending));
    }



    @Operation(summary = OPERATION_SUMMARY_INCREASE_ITEM_QUANTITY, description = OPERATION_DESCRIPTION_INCREASE_ITEM_QUANTITY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_201, description = RESPONSE_201_DESCRIPTION_INCREASE_ITEM,
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = STATUS_400, description = RESPONSE_400_DESCRIPTION_INCREASE_ITEM,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = STATUS_500, description = RESPONSE_500_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}/{increase}")
    public ResponseEntity<Void> increaseItem(@PathVariable Integer id, @PathVariable Integer increase) {
        iItemHandler.increaseItem(id, increase);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
