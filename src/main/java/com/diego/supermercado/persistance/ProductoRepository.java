package com.diego.supermercado.persistance;

import com.diego.supermercado.domain.Product;
import com.diego.supermercado.domain.repository.ProductRepository;
import com.diego.supermercado.persistance.crud.ProductoCrudRepository;
import com.diego.supermercado.persistance.entity.Producto;
import com.diego.supermercado.persistance.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        //recupera los productos (entity) de la BD, y los mete en una lista
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        //convierte los Producto en Products
        return productos.map(producto -> productMapper.toProducts(producto));
    }

    public Optional<List<Producto>> getMasVendidos(Integer cant, Boolean estado){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cant, estado);
    }

    @Override
    public Product save(Product prod) {
        Producto producto = productMapper.toProducto(prod);
        return productMapper.toProduct( productoCrudRepository.save(producto)) ;
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> getProduct(int productId) {
       /* opcion 1
       Optional<Producto> producto = productoCrudRepository.findById(productId);
        Producto prod = producto.get();
        Product product = productMapper.toProduct(prod);
        return Optional.of(product);*/
        // opcion 2
        return productoCrudRepository.findById(productId).map(prod -> productMapper.toProduct(prod));
    }

}
