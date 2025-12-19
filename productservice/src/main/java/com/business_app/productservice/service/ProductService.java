package com.business_app.productservice.service;

import com.business_app.productservice.dto.ProductCreateRequest;
import com.business_app.productservice.dto.ProductResponse;
import com.business_app.productservice.dto.ProductUpdateRequest;
import com.business_app.productservice.exception.ProductNotFoundException;
import com.business_app.productservice.mapper.ProductMapper;
import com.business_app.productservice.model.Product;
import com.business_app.productservice.model.ProductStatus;
import com.business_app.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductCreateRequest request) {

        log.info("Creating product: {}", request.getName());

        Product product = ProductMapper.toEntity(request);
        Product saved = productRepository.save(product);

        log.info("Product created with id {}", saved.getId());

        return ProductMapper.toResponse(saved);
    }

    public List<ProductResponse> getAllActive() {

        log.info("Fetching active products");

        return productRepository.findByStatus(ProductStatus.ACTIVE)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse getById(String id) {

        log.info("Fetching product {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toResponse(product);
    }

    public ProductResponse update(String id, ProductUpdateRequest request) {
        log.info("Updating product {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toResponse(productRepository.save(ProductMapper.toUpdateResponse(product,request)));
    }

    public void delete(String id) {
        log.info("Deleting product {}", id);
        productRepository.deleteById(id);
    }
}
