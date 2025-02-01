package com.practice.Testing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.Testing.entity.Product;
import com.practice.Testing.exception.ProductNotFoundException;
import com.practice.Testing.repository.ProductRepository;
import com.practice.Testing.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAllByOrderByUpdatedAtDesc();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ProductNotFoundException("No products found by id : " + id);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            return savedProduct;
        } catch (Exception e) {
            throw new ProductNotFoundException("No products saved");
        }

    }

    @Override
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ProductNotFoundException("No products deleted by id : " + id);
        }
    }

    @Override
    public Product update(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

}
