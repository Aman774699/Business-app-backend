package com.business_app.productservice.controller;

import com.business_app.productservice.dto.ProductCreateRequest;
import com.business_app.productservice.dto.ProductResponse;
import com.business_app.productservice.dto.ProductUpdateRequest;
import com.business_app.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * ADMIN API
     * Create a new product
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request) {

        log.info("Creating product | name={}, category={}, price={}",
                request.getName(),
                request.getCategory(),
                request.getPrice());

        ProductResponse response = service.create(request);

        log.info("Product created successfully | productId={}", response.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * CUSTOMER API
     * Fetch all ACTIVE products
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        log.debug("Fetching all active products");

        List<ProductResponse> products = service.getAllActive();

        log.info("Fetched {} active products", products.size());

        return ResponseEntity.ok(products);
    }

    /**
     * CUSTOMER API
     * Fetch product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable String id) {

        log.debug("Fetching product by id={}", id);

        ProductResponse response = service.getById(id);

        log.info("Product fetched successfully | productId={}", id);

        return ResponseEntity.ok(response);
    }

    // ADMIN
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody ProductUpdateRequest request) {

        log.info("Updating product id={}", id);
        return ResponseEntity.ok(service.update(id, request));
    }

    // ADMIN (Soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {

        log.info("Deleting product id={}", id);
        service.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
