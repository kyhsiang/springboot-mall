package com.kyhsiang.springbootmall.dao;

import com.kyhsiang.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(Product product);
}
