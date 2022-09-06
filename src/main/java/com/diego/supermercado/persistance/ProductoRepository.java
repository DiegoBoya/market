package com.diego.supermercado.persistance;

import com.diego.supermercado.persistance.crud.ProductoCrudRepository;
import com.diego.supermercado.persistance.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    // usa queryMethod
    public List<Producto> getByCategoria(Integer idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    Optional<List<Producto>> getMasVendidos(Integer cant, Boolean estado){
        return productoCrudRepository.findBtCantidadStockLessThanAndEstado(cant, estado);
    }
}
