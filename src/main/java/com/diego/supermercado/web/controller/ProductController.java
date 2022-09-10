package com.diego.supermercado.web.controller;

import com.diego.supermercado.domain.Product;
import com.diego.supermercado.domain.service.ProductService;
import com.diego.supermercado.persistance.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId)   {
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{id}")
    public Optional<List<Product>> getProductByCategory(@PathVariable("id")int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") int productId){
        return productService.delete(productId);
    }

    @PutMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int productId, @RequestBody Product product){
        productService.update(productId, product);
    }


}
