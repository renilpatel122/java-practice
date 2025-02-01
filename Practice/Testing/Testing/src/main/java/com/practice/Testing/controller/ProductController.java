package com.practice.Testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practice.Testing.entity.Product;
import com.practice.Testing.service.ProductService;
import com.practice.Testing.handler.ResponseHandler;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Getting all Product", products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get Product", product);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Product Saved Successfully", savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Product Deleted Successfully", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Product Updated Successfully", updatedProduct);
    }
}
