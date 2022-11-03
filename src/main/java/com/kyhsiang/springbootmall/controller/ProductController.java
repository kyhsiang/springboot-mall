package com.kyhsiang.springbootmall.controller;

import com.kyhsiang.springbootmall.model.Product;
import com.kyhsiang.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Integer productId = productService.createProduct(product);
        Product newProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
}
