package com.adison.crud1033.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "orders") // ชื่อของตารางในฐานข้อมูล
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber; // รหัสคำสั่งซื้อ

    private String username; // ชื่อผู้สั่งซื้อ

    private String status = "Pending";  // สถานะของคำสั่งซื้อ

    private double totalAmount;

    private String address;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>(); // กำหนดค่าเริ่มต้น

    // Constructors
    public Order(String orderNumber, String username, String status, String address,LocalDateTime orderDate) {
        this.orderNumber = orderNumber;
        this.username = username;
        this.status = status;
        this.address = address;
        this.orderDate = (orderDate != null) ? orderDate : LocalDateTime.now(); // Set to now if null
    }

    public Order() {
        this.orderItems = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getters และ Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return (orderDate != null) ? orderDate.format(formatter) : null; // Handle null
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) { // ตรวจสอบค่า status
            this.status = "Pending"; // กำหนดค่าเริ่มต้นเป็น Pending
        } else {
            this.status = status;
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for (OrderItem item : orderItems) {
            item.setOrder(this); // เชื่อมโยง OrderItem กับ Order
        }
    }

    // Method for adding an OrderItem to the Order
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this); // ตั้งค่า Order ใน OrderItem
    }

    public void clearOrderItems() {
        orderItems.clear(); // ลบทุก OrderItem ในรายการ
    }

    // Method for removing an OrderItem from the Order
    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null); // ลบการเชื่อมโยง
    }
}
