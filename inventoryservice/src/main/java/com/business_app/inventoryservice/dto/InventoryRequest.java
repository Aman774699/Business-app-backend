package com.business_app.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryRequest {
    private String productId;
    private int availableQuantity;
}
