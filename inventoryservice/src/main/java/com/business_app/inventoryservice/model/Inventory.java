package com.business_app.inventoryservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "inventory")
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String productId;
    private int availableQuantity;
    private int reservedQuantity;
    @Version
    private Long version;


}
