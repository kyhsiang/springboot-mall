package com.kyhsiang.springbootmall.service;


import com.kyhsiang.springbootmall.constant.ProductCategory;
import com.kyhsiang.springbootmall.dto.ProductRequest;
import com.kyhsiang.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
