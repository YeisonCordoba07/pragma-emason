package com.pragma.emason.application.handler.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "addsupply", url = "http://localhost:8082/supply", configuration = FeignClientConfig.class)
public interface SupplyFeignClient {

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //ResponseEntity<Void> increaseSupply(@RequestBody SupplyRequestDTO supplyRequestDTO);
}
