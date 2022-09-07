package com.diego.supermercado.persistance.crud;

import com.diego.supermercado.persistance.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository  extends CrudRepository<Producto, Integer> {


    //Con queryMethod
    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    /**
     *  atribyto de busqueda = cantidad stock
     *  operador = les than
     *  atrobuto extra = estado
     */
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);

}
