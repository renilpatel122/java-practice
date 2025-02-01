package com.practice.Testing.service;

import java.util.List;

import com.practice.Testing.entity.Product;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    Product update(Long id, Product product);
}
