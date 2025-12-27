package com.business_app.inventoryservice.service;

import com.business_app.inventoryservice.dto.InventoryRequest;
import com.business_app.inventoryservice.dto.InventoryResponse;
import com.business_app.inventoryservice.mapper.InventoryMapper;
import com.business_app.inventoryservice.model.Inventory;
import com.business_app.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse createOrUpdateStock(InventoryRequest inventoryRequest) {
        Inventory inventory=inventoryRepository.findByProductId(inventoryRequest.getProductId())
                .orElseThrow(()->new RuntimeException("Product not found"));
        log.info("Create Or Update Inventory Request");
        Inventory saved=inventoryRepository.save(InventoryMapper.updateOrCreateInventoryMapper(inventory, inventoryRequest));
        log.info("Inventory update for Product_Id"+inventoryRequest.getProductId());
        return InventoryMapper.toResponse(saved);
    }

    public void reverseStock(String productId,int quantity)
    {
        Inventory inventory=inventoryRepository.findByProductId(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if(inventory.getAvailableQuantity()<quantity)
        {
            throw new RuntimeException("Insufficient stock for the product"+productId);
        }
        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity
        );
        inventory.setReservedQuantity(
                inventory.getReservedQuantity() + quantity
        );
        inventoryRepository.save(inventory);
        log.info("Inventory reverse for Product_Id"+inventory.getProductId());
    }

    public void releaseStock(String productId,int quantity)
    {
      Inventory inventory=inventoryRepository.findByProductId(productId)
              .orElseThrow(()->new RuntimeException("Inventory not found Exception"));
        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() + quantity
        );
        inventory.setReservedQuantity(
                inventory.getReservedQuantity() - quantity
        );

        inventoryRepository.save(inventory);

        log.info("Released {} units for productId={}", quantity, productId);

    }



}
