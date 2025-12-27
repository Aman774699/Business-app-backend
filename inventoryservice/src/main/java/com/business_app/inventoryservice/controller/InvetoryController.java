package com.business_app.inventoryservice.controller;

import com.business_app.inventoryservice.dto.InventoryRequest;
import com.business_app.inventoryservice.dto.InventoryResponse;
import com.business_app.inventoryservice.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventry")
@Slf4j
public class InvetoryController {
    private final InventoryService inventoryService;
    public InvetoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create or update inventory",
            description = "Admin can add or update stock for a product"
    )
    @ApiResponse(responseCode = "200", description = "Inventory updated successfully")
    @PostMapping
    public ResponseEntity<InventoryResponse> createOrUpdate(
            @RequestBody InventoryRequest request) {

        return ResponseEntity.ok(inventoryService.createOrUpdateStock(request));
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(
            summary = "Reverse Stock",
            description ="Reserve stock when product is added to cart"
    )
    @ApiResponse(responseCode = "200", description = "Stock reserved")
    @PostMapping("/reserve")
    public ResponseEntity<Void> reserve(@RequestParam String productId,@RequestParam int quantity) {
      inventoryService.releaseStock(productId, quantity);
      return ResponseEntity.ok().build();
    }
}
