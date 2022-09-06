package com.diego.supermercado.persistance.crud;

import com.diego.supermercado.persistance.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository  extends CrudRepository<Producto, Integer> {
}
