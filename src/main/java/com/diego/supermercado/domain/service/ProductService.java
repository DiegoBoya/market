package com.diego.supermercado.domain.service;

import com.diego.supermercado.domain.Product;
import com.diego.supermercado.domain.repository.ProductRepository;
import com.diego.supermercado.persistance.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepositoryImpl;

    public List<Product> getAll(){
        return productRepositoryImpl.getAll();
    }


    public Optional<Product> getProduct(int productId) {
        return productRepositoryImpl.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepositoryImpl.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepositoryImpl.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepositoryImpl.delete(productId);
            return true;
        }).orElse(false);
    }
}

