package com.kyhsiang.springbootmall.service.impl;

import com.kyhsiang.springbootmall.dao.ProductDao;
import com.kyhsiang.springbootmall.dto.ProductQueryParams;
import com.kyhsiang.springbootmall.dto.ProductRequest;
import com.kyhsiang.springbootmall.model.Product;
import com.kyhsiang.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    //獲取商品總數
    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }

    //獲取商品列表
    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    //藉由商品編號獲取商品資訊
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    //新增商品
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    //修改商品資訊
    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    //藉由商品編號刪除商品
    @Override
    public void deleteProductById(Integer productId) {
        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        productDao.deleteProductById(productId);
    }
}
