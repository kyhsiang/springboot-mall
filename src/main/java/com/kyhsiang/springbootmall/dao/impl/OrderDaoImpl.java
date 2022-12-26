package com.kyhsiang.springbootmall.dao.impl;

import com.kyhsiang.springbootmall.dao.OrderDao;
import com.kyhsiang.springbootmall.dto.OrderQueryParams;
import com.kyhsiang.springbootmall.mapper.OrderItemRowMapper;
import com.kyhsiang.springbootmall.mapper.OrderRowMapper;
import com.kyhsiang.springbootmall.model.Order;
import com.kyhsiang.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //新增訂單
    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order`(user_id, total_amount, created_date, last_modified_date) VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    //新增訂單詳細內容
    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount) VALUES (:orderId, :productId, :quantity, :amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    //藉由訂單編號獲取訂單
    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date FROM `order` WHERE order_id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    //藉由訂單編號獲取訂單詳細內容
    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url " +
                "FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
        return orderItemList;
    }

    //獲取特定會員的所有訂單
    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date FROM `order` WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        //查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);
        //排序
        sql = sql + " ORDER BY created_date DESC";
        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        return orderList;
    }

    //獲取特定會員的訂單總數
    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        //查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if (orderQueryParams.getUserId() != null) {
            sql = sql + " AND user_id = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }
}
