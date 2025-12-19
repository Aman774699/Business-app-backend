package com.business_app.productservice.mapper;

import com.business_app.productservice.dto.ProductCreateRequest;
import com.business_app.productservice.dto.ProductResponse;
import com.business_app.productservice.dto.ProductUpdateRequest;
import com.business_app.productservice.model.Product;

import java.time.LocalDateTime;

public class ProductMapper {
    public ProductMapper() {
    }

    public static Product toEntity(ProductCreateRequest req) {
        return Product.builder()
                .name(req.getName())
                .category(req.getCategory())
                .subCategory(req.getSubCategory())
                .description(req.getDescription())
                .price(req.getPrice())
                .material(req.getMaterial())
                .dimensions(req.getDimensions())
                .images(req.getImages())
                .status(req.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static ProductResponse toResponse(Product product) {
        ProductResponse res = new ProductResponse();
        res.setId(product.getId());
        res.setName(product.getName());
        res.setCategory(product.getCategory());
        res.setSubCategory(product.getSubCategory());
        res.setDescription(product.getDescription());
        res.setPrice(product.getPrice());
        res.setMaterial(product.getMaterial());
        res.setDimensions(product.getDimensions());
        res.setImages(product.getImages());
        return res;
    }

    public static Product toUpdateResponse(Product product,ProductUpdateRequest productUpdateRequest)
    {
        product.setName(productUpdateRequest.getName());
        product.setDescription(productUpdateRequest.getDescription());
        product.setPrice(productUpdateRequest.getPrice());
        product.setStatus(productUpdateRequest.getStatus());
        return product;
    }
}
