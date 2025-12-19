package com.business_app.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private String id;

    private String name;

    private String category;      // Living Room, Bedroom, Office
    private String subCategory;   // Sofa, Bed, Table

    private String description;

    private BigDecimal price;

    private String material;      // Wood, Plywood, Steel

    private Dimensions dimensions;

    private List<String> images;  // image URLs

    private ProductStatus status; // ACTIVE, INACTIVE, DRAFT

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
