package com.adison.crud1033.services;

import com.adison.crud1033.entity.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);


    List<Order> findOrdersByStatus(String status);

    List<Order> getOrdersByUsername(String username);

    boolean updateOrderStatus(String orderNumber, String status);

    Order save(int id, Order order);

    List<Order> findAll();

    Order findById(Integer id);

    void deleteById(Integer id);
}
