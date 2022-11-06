package com.kyhsiang.springbootmall.service;


import com.kyhsiang.springbootmall.dto.ProductQueryParams;
import com.kyhsiang.springbootmall.dto.ProductRequest;
import com.kyhsiang.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
