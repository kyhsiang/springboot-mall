package com.kyhsiang.springbootmall.service;


import com.kyhsiang.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(Product product);

}
