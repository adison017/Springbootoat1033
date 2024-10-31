package com.adison.crud1033.services;

import com.adison.crud1033.entity.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category catrgory);

    Category save(int id, Category catrgory);

    List<Category> findAll();

    Category findById(Integer id);

    void deleteById(Integer id);
}
