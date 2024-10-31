package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.Product;
import com.adison.crud1033.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private ProductService productService;

    @Autowired
    public  ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/Products")
    public Product addProduct(@RequestBody Product product){
        product.setId(0);
        return  productService.save(product);
    }

    @PutMapping("/Products/decrease")
    public String decreaseProductQuantity(
            @RequestParam("name") String name,
            @RequestParam("stock") int stock) {
        if (name == null || name.isEmpty() || stock <= 0) {
            throw new IllegalArgumentException("Name or quantity is not valid");
        }
        productService.decreaseProductStock(name, stock);
        return "Product quantity decreased successfully";
    }


    @GetMapping("/Products")
    public List<Product> getAllProduct(){
        return  productService.findAll();
    }

    @GetMapping("/Products/{id}")
    public Product getProduct(@PathVariable long id){
        Product myProduct = productService.findById(id);
        if (myProduct ==null) {
            throw  new RuntimeException("\n" +
                    "No user information found "+id);
        }
        return myProduct;
    }

    @DeleteMapping("/Products/{id}")
    public String deleteProduct(@PathVariable long id){
        Product myProduct =  productService.findById(id);

        if(myProduct ==null){
            throw  new RuntimeException("\n" +
                    "No user information found. "+id);
        }
        productService.deleteById(id);
        return "Delete user ID information "+id+" finished!!";
    }

    @PutMapping("/Products")
    public Product updateProduct(@RequestBody Product product){
        return  productService.save(product);
    }

    @PutMapping("/Products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        // ค้นหาผู้ใช้ด้วย id และทำการอัปเดต
        return productService.save(id, product); // ตัวอย่างการเรียก service สำหรับการอัปเดต
    }

}
