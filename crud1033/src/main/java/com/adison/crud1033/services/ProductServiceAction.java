package com.adison.crud1033.services;

import com.adison.crud1033.entity.Product;
import com.adison.crud1033.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceAction implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceAction(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        validateProduct(product); // ตรวจสอบความถูกต้องของผลิตภัณฑ์
        return productRepository.save(product);
    }

    @Override
    public void decreaseProductStock(String name, int stock) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            int newStock = product.getStock() - stock; // สมมติว่าไม่มี getter/setter สำหรับ stock
            if (newStock < 0) {
                throw new IllegalArgumentException("Product stock cannot be less than zero");
            }
            product.setStock(newStock); // สมมติว่าไม่มี getter/setter สำหรับ stock
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }


    @Override
    public Product save(long id, Product product) {
        Product existingProduct = findById(id); // ค้นหาผู้ใช้ที่มี ID
        // อัปเดตข้อมูลผลิตภัณฑ์
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());

        validateProduct(existingProduct); // ตรวจสอบความถูกต้องก่อนบันทึก
        return productRepository.save(existingProduct); // บันทึกการเปลี่ยนแปลง
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("ไม่พบข้อมูลผลิตภัณฑ์  " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    // ฟังก์ชันสำหรับตรวจสอบความถูกต้องของข้อมูลผลิตภัณฑ์
    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new RuntimeException("ชื่อผลิตภัณฑ์ไม่สามารถว่างเปล่าได้");
        }
        if (product.getPrice() < 0) {
            throw new RuntimeException("ราคาผลิตภัณฑ์ต้องมากกว่าหรือเท่ากับ 0");
        }
        if (product.getStock() < 0) {
            throw new RuntimeException("สต็อกผลิตภัณฑ์ต้องไม่ต่ำกว่า 0");
        }
    }
}
