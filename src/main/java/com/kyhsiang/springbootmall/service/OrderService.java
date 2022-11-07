package com.kyhsiang.springbootmall.service;

import com.kyhsiang.springbootmall.dto.CreateOrderRequest;
import com.kyhsiang.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
