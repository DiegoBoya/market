package com.diego.supermercado.domain.repository;

import com.diego.supermercado.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    //retorna una lista de productos que tengan un sotck menor al pasado de parametro
    Optional<List<Product>> getScarseProducts(int quantity);
    Product save(Product prod);
    void delete(int productId);
    Optional<Product> getProduct(int productId);

    //void update(int productId, Product product);
}
