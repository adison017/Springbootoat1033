package com.adison.crud1033.services;

import com.adison.crud1033.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    void decreaseProductStock(String name, int stock);

    Product save(long id, Product user);

    List<Product> findAll();

    Product findById(Long id);

    void deleteById(Long id);
}
