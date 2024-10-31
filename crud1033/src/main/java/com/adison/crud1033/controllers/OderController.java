package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.Order;
import com.adison.crud1033.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OderController {
    private OrderService orderService;

    @Autowired
    public  OderController(OrderService orderService){

        this.orderService=orderService;
    }

    @GetMapping("/orders/status")
    public List<Order> getOrdersByStatus(@RequestParam("status") String status) {
        return orderService.findOrdersByStatus(status);
    }

    @GetMapping("/orders/user/{username}")
    public ResponseEntity<List<Order>> getOrdersByUsername(@PathVariable String username) {
        List<Order> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/orders/updateStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestBody OrderUpdateRequest orderUpdateRequest) {
        boolean isUpdated = orderService.updateOrderStatus(orderUpdateRequest.getOrderNumber(), orderUpdateRequest.getStatus());
        if (isUpdated) {
            return ResponseEntity.ok("Order status updated successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to update order status.");
        }
    }

    public static class OrderUpdateRequest {
        private String orderNumber;
        private String status;

        // Getters and Setters
        public String getOrderNumber() { return orderNumber; }
        public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        // บันทึกคำสั่งซื้อโดยใช้บริการ
        Order savedOrder = orderService.save(order);

        // ส่งคืนคำสั่งซื้อที่บันทึกในฐานข้อมูลพร้อมสถานะ 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrder(){
        return  orderService.findAll();
    }



    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id){
        Order myOrder = orderService.findById(id);
        if (myOrder ==null) {
            throw  new RuntimeException("\n" +
                    "No user information found "+id);
        }
        return myOrder;
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable int id){
        Order myOrder =  orderService.findById(id);

        if(myOrder ==null){
            throw  new RuntimeException("\n" +
                    "No user information found. "+id);
        }
        orderService.deleteById(id);
        return "Delete user ID information "+id+" finished!!";
    }

    @PutMapping("/orders")
    public Order updateOrder(@RequestBody Order order){
        return  orderService.save(order);
    }
    @PutMapping("/orders/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        // ค้นหาผู้ใช้ด้วย id และทำการอัปเดต
        return orderService.save(id, order); // ตัวอย่างการเรียก service สำหรับการอัปเดต
    }
}
