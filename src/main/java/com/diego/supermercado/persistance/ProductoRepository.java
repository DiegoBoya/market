package com.diego.supermercado.persistance;

import com.diego.supermercado.persistance.crud.ProductoCrudRepository;
import com.diego.supermercado.persistance.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
