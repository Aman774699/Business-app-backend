package com.business_app.productservice.dto;

import com.business_app.productservice.model.Dimensions;
import com.business_app.productservice.model.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class ProductUpdateRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private ProductStatus status;
    private List<String> images;
}
