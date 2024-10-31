package com.adison.crud1033.services;

import com.adison.crud1033.entity.Brand;
import com.adison.crud1033.repository.BrandRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceAction implements BrandService {
    private BrandRepository brandRepository;
    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand save(int brand_id, Brand brand) {
        Brand existingBrand = findById(brand_id); // ค้นหาผู้ใช้ที่มี ID
        // อัปเดตข้อมูลผู้ใช้
        existingBrand.setBrand_name(brand.getBrand_name());


        return brandRepository.save(existingBrand); // บัน
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Integer brand_id) {
        Optional<Brand> result = brandRepository.findById(brand_id);
        Brand data=null;
        if(result.isPresent()){
            data=result.get();
        }else {
            throw  new RuntimeException("ไม่พบข้อมูลผู้ใช้  "+brand_id);
        }
        return data;
    }

    @Override
    public void deleteById(Integer brand_id) {
        brandRepository.deleteById(brand_id);
    }

    public BrandServiceAction(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
}
