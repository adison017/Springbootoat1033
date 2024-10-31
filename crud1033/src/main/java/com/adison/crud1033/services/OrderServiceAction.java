package com.adison.crud1033.services;

import com.adison.crud1033.entity.Order;
import com.adison.crud1033.entity.OrderItem;
import com.adison.crud1033.repository.OrderRepository;
import com.adison.crud1033.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceAction implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;



    @Override
    public Order save(Order order) {
        // ตรวจสอบและตั้งค่า status หาก null
        if (order.getStatus() == null) {
            order.setStatus("Pending"); // กำหนดค่าเริ่มต้น
        }

        // ตรวจสอบและตั้งค่า order number สำหรับ order items
        if (order.getOrderItems() != null) { // ตรวจสอบว่ามี order items หรือไม่
            for (OrderItem item : order.getOrderItems()) {
                item.setOrdernumber(order.getOrderNumber()); // กำหนดค่า ordernumber
                item.setOrder(order); // เชื่อมโยงกับ Order
            }
        }

        // คำนวณยอดรวมของคำสั่งซื้อ
        double total = 0;
        for (OrderItem item : order.getOrderItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        order.setTotalAmount(total); // บันทึกยอดรวมใน order

        // บันทึก Order ลงในฐานข้อมูล
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    @Override
    public boolean updateOrderStatus(String orderNumber, String status) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Order save(int id, Order order) {
        Order existingOrder = findById(id); // ค้นหาผู้ใช้ที่มี ID
        // อัปเดตข้อมูลผู้ใช้
        existingOrder.setUsername(order.getUsername());
        existingOrder.setOrderNumber(order.getOrderNumber());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setOrderItems(order.getOrderItems());
        // อัปเดตรายการสินค้าใน Order


        return orderRepository.save(existingOrder); // บัน
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        Optional<Order> result = orderRepository.findById(id);
        Order data=null;
        if(result.isPresent()){
            data=result.get();
        }else {
            throw  new RuntimeException("ไม่พบข้อมูลผู้ใช้  "+id);
        }
        return data;
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public OrderServiceAction(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
