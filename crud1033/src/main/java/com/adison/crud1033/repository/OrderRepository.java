package com.adison.crud1033.repository;

import com.adison.crud1033.entity.Order;
import com.adison.crud1033.entity.OrderItem;
import com.adison.crud1033.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUsername(String username);
    List<Order> findByStatus(String status);
    Order findByOrderNumber(String orderNumber);
}
