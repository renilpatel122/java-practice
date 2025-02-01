package com.practice.product_service.service;

import com.practice.product_service.entity.ProductRequest;
import com.practice.product_service.entity.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();

}
