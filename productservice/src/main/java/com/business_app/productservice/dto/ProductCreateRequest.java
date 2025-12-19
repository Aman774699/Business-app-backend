package com.business_app.productservice.dto;


import com.business_app.productservice.model.Dimensions;
import com.business_app.productservice.model.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductCreateRequest {
    @NotBlank
    private String name;

    private String category;
    private String subCategory;
    private String description;

    @NotNull
    private BigDecimal price;

    private String material;
    private Dimensions dimensions;

    private List<String> images;

    @NotNull
    private ProductStatus status;
}
