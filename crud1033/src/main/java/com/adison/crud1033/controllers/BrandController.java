package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.Brand;
import com.adison.crud1033.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @PostMapping("/Brand")
    public Brand addBrand(@RequestBody Brand brand){
        return  brandService.save(brand);
    }

    @GetMapping("/Brand")
    public List<Brand> getAllbrand(){
        return  brandService.findAll();
    }

    @GetMapping("/Brand/{id}")
    public Brand getBrand(@PathVariable int id){
        Brand myBrand = brandService.findById(id);
        if (myBrand==null) {
            throw  new RuntimeException("\n" +
                    "No brand information found. "+id);
        }
        return  myBrand;
    }

    @DeleteMapping("/Brand/{id}")
    public String deleteBrand(@PathVariable int id){
        Brand myBrand =  brandService.findById(id);

        if(myBrand==null){
            throw  new RuntimeException("\n" +
                    "No brand information found "+id);
        }
        brandService.deleteById(id);
        return "Delete brand ID information "+id+" finished!!";
    }

    @PutMapping("/Brand")
    public Brand updateBrand(@RequestBody Brand brand){
        return  brandService.save(brand);
    }
    @PutMapping("/Brand/{id}")
    public Brand updateBrand(@PathVariable int id, @RequestBody Brand brand) {
        // ค้นหาผู้ใช้ด้วย id และทำการอัปเดต
        return brandService.save(id, brand); // ตัวอย่างการเรียก service สำหรับการอัปเดต
    }
}
