package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.OrderItem;
import com.adison.crud1033.repository.OrderItemRepository;
import com.adison.crud1033.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_items")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    // GET: ดึงรายการสินค้าในคำสั่งซื้อทั้งหมด
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }
    
    @GetMapping( "/orderNumber")
    public List<OrderItem> getOrderItems(@RequestParam String orderNumber) {
        return orderItemRepository.findByOrderOrderNumber(orderNumber); // ต้องมีการกรองด้วย orderNumber
    }


    // GET: ดึงข้อมูล order_item ตาม ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    // POST: เพิ่ม order_item ใหม่
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem newOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(newOrderItem);
    }

    // PUT: แก้ไขข้อมูล order_item ตาม ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDetails);
        return ResponseEntity.ok(updatedOrderItem);
    }

    // DELETE: ลบ order_item ตาม ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
