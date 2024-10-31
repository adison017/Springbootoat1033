package com.adison.crud1033.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brand_id;
    private String brand_name; // รหัสคำสั่งซื้อ

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
    public Brand() {
    }

    public Brand(int brand_id, String brand_name) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }
}