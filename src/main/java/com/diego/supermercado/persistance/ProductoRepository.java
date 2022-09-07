package com.diego.supermercado.persistance;

import com.diego.supermercado.persistance.crud.ProductoCrudRepository;
import com.diego.supermercado.persistance.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    // usa queryMethod
    public List<Producto> getByCategoria(Integer idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getMasVendidos(Integer cant, Boolean estado){
        return productoCrudRepository.findBtCantidadStockLessThanAndEstado(cant, estado);
    }

    public Optional<Producto> getProducto(Integer idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(Integer idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
