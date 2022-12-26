package com.kyhsiang.springbootmall.service.impl;

import com.kyhsiang.springbootmall.dao.OrderDao;
import com.kyhsiang.springbootmall.dao.ProductDao;
import com.kyhsiang.springbootmall.dao.UserDao;
import com.kyhsiang.springbootmall.dto.BuyItem;
import com.kyhsiang.springbootmall.dto.CreateOrderRequest;
import com.kyhsiang.springbootmall.dto.OrderQueryParams;
import com.kyhsiang.springbootmall.model.Order;
import com.kyhsiang.springbootmall.model.OrderItem;
import com.kyhsiang.springbootmall.model.Product;
import com.kyhsiang.springbootmall.model.User;
import com.kyhsiang.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    //新增訂單
    @Transactional
    @Override
    public synchronized Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查user是否存在
        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            //檢查product是否存在、庫存是否足夠
            if (product == null) {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}",
                buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            //計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        //創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }

    //藉由訂單編號獲取訂單
    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    //獲取所有訂單
    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);
        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    //獲取訂單總數
    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }
}
