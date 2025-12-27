package com.business_app.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryResponse {
    private String productId;
    private int availableQuantity;
    private int reservedQuantity;
}
