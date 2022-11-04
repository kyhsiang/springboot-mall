package com.kyhsiang.springbootmall.dao;

import com.kyhsiang.springbootmall.dto.ProductRequest;
import com.kyhsiang.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
