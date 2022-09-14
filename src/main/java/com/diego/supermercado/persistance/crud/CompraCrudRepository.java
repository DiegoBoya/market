package com.diego.supermercado.persistance.crud;

import com.diego.supermercado.persistance.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    //queryMethod
    Optional<List<Compra>> findByIdCliente(String idCliente);

}
