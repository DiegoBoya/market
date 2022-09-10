package com.diego.supermercado.domain.service;

import com.diego.supermercado.domain.Product;
import com.diego.supermercado.domain.repository.ProductRepository;
import com.diego.supermercado.persistance.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class.getName());
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

    /**
     *
     * @param productId = product to update
     * @param product = New values to save for the selected product
     */
    public void update (int productId , Product product){
        //recupera el product a modificar
        Product prod = getProduct(productId).get();
        // todo: hacerlo con funcional con el map()
        if (prod != null) {
            prod.setName(product.getName());
            prod.setCategory(product.getCategory());
            prod.setActive(product.isActive());
            prod.setPrice(product.getPrice());
            prod.setStock(product.getStock());
            this.save(prod);
            logger.info("se modifico el Producto con exito.");
        } else {
            logger.info("no se encuentra el producto con el id: " + productId);
        }

    }
}

