package com.adison.crud1033.services;

import com.adison.crud1033.entity.OrderItem;

import java.util.List;

public interface OrderItemService2 {
    List<OrderItem> getOrderItemsByOrderNumber(String orderNumber);

    // ดึงข้อมูล order_item ตาม ID
    OrderItem getOrderItemById(Long id);

    // เพิ่ม order_item ใหม่
    OrderItem createOrderItem(OrderItem orderItem);

    // แก้ไขข้อมูล order_item ตาม ID
    OrderItem updateOrderItem(Long id, OrderItem orderItemDetails);

    // ลบ order_item ตาม ID
    void deleteOrderItem(Long id);
}
