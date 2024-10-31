package com.adison.crud1033.services;

import com.adison.crud1033.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand save(Brand brand);

    Brand save(int brand_id, Brand brand);

    List<Brand> findAll();

    Brand findById(Integer brand_id);

    void deleteById(Integer brand_id);
}
