package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.Category;
import com.adison.crud1033.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CaategoryController {
    private CategoryService categoryService;

    @Autowired
    public CaategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/category")
    public Category addCategory(@RequestBody Category category){
        return  categoryService.save(category);
    }

    @GetMapping("/category")
    public List<Category> getAllcategory(){
        return  categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable int id){
        Category myCategory = categoryService.findById(id);
        if (myCategory==null) {
            throw  new RuntimeException("\n" +
                    "No category information found. "+id);
        }
        return  myCategory;
    }

    @DeleteMapping("/category/{id}")
    public String deleteCategory(@PathVariable int id){
        Category myCategory =  categoryService.findById(id);

        if(myCategory==null){
            throw  new RuntimeException("\n" +
                    "No category information found "+id);
        }
        categoryService.deleteById(id);
        return "Delete category ID information "+id+" finished!!";
    }

    @PutMapping("/category")
    public Category updateCategory(@RequestBody Category category){
        return  categoryService.save(category);
    }
    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        // ค้นหาผู้ใช้ด้วย id และทำการอัปเดต
        return categoryService.save(id, category); // ตัวอย่างการเรียก service สำหรับการอัปเดต
    }
}
