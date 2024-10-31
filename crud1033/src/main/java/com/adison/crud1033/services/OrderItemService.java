package com.adison.crud1033.services;

import com.adison.crud1033.entity.OrderItem;
import com.adison.crud1033.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements OrderItemService2 {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // ดึงรายการสินค้าในคำสั่งซื้อทั้งหมด
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
    @Override
    public List<OrderItem> getOrderItemsByOrderNumber(String orderNumber) {
        return orderItemRepository.findByOrderOrderNumber(orderNumber);
    }
    // ดึงข้อมูล order_item ตาม ID
    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ไม่พบ order item ที่มี ID: " + id));
    }

    // เพิ่ม order_item ใหม่
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // แก้ไขข้อมูล order_item ตาม ID
    @Override
    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        OrderItem orderItem = getOrderItemById(id);

        orderItem.setProduct_Name(orderItemDetails.getProduct_Name());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setPrice(orderItemDetails.getPrice());

        return orderItemRepository.save(orderItem);
    }

    // ลบ order_item ตาม ID
    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = getOrderItemById(id);
        orderItemRepository.delete(orderItem);
    }
}
