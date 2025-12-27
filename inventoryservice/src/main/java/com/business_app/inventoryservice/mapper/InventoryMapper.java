package com.business_app.inventoryservice.mapper;

import com.business_app.inventoryservice.dto.InventoryRequest;
import com.business_app.inventoryservice.dto.InventoryResponse;
import com.business_app.inventoryservice.model.Inventory;

public class InventoryMapper {

    public static Inventory updateOrCreateInventoryMapper(Inventory inventory, InventoryRequest inventoryRequest) {
        inventory.setProductId(inventoryRequest.getProductId());
        inventory.setAvailableQuantity(inventoryRequest.getAvailableQuantity());
        return inventory;
    }

    public static InventoryResponse toResponse(Inventory inventory) {
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setProductId(inventory.getProductId());
        inventoryResponse.setAvailableQuantity(inventory.getAvailableQuantity());
        inventoryResponse.setReservedQuantity(inventory.getReservedQuantity());
        return inventoryResponse;
    }

}
