package com.kyhsiang.springbootmall.service;

import com.kyhsiang.springbootmall.dto.CreateOrderRequest;
import com.kyhsiang.springbootmall.dto.OrderQueryParams;
import com.kyhsiang.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
