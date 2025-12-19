package com.business_app.productservice.dto;

import com.business_app.productservice.model.Dimensions;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String category;
    private String subCategory;
    private String description;
    private BigDecimal price;
    private String material;
    private Dimensions dimensions;
    private List<String> images;
}
