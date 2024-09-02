package com.pragma.emason.infrastructure.input.rest;

import com.pragma.emason.application.dto.ItemRequestDTO;
import com.pragma.emason.application.handler.IItemHandler;
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
@RequestMapping("/item")
@RequiredArgsConstructor
@Validated
public class ItemRestController {
    private final IItemHandler iItemHandler;

    @PostMapping
    public ResponseEntity<Void> saveItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO){
        iItemHandler.saveItemInDataBase(itemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
