package com.business_app.productservice.repository;

import com.business_app.productservice.model.Product;
import com.business_app.productservice.model.ProductStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByStatus(ProductStatus status);

    List<Product> findByCategoryAndStatus(String category, ProductStatus status);
}
