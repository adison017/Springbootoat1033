package com.adison.crud1033.services;

import com.adison.crud1033.entity.Category;
import com.adison.crud1033.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceAction implements CategoryService {
    private CategoryRepository categoryRepository;
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category save(int cat_id, Category category) {
        Category existingCategory = findById(cat_id); // ค้นหาผู้ใช้ที่มี ID
        // อัปเดตข้อมูลผู้ใช้
        existingCategory.setCat_name(category.getCat_name());


        return categoryRepository.save(existingCategory); // บัน
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer cat_id) {
        Optional<Category> result = categoryRepository.findById(cat_id);
        Category data=null;
        if(result.isPresent()){
            data=result.get();
        }else {
            throw  new RuntimeException("ไม่พบข้อมูลผู้ใช้  "+cat_id);
        }
        return data;
    }

    @Override
    public void deleteById(Integer cat_id) {
        categoryRepository.deleteById(cat_id);
    }

    public CategoryServiceAction(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
