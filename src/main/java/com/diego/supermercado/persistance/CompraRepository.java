package com.diego.supermercado.persistance;

import com.diego.supermercado.domain.dto.Purchase;
import com.diego.supermercado.domain.repository.PurchaseRepository;
import com.diego.supermercado.persistance.crud.CompraCrudRepository;
import com.diego.supermercado.persistance.entity.Compra;
import com.diego.supermercado.persistance.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    //findByIdCliente devuelve un Optional, si en el optional hay algo, se ejecuta
    // el .map() que conviere el tipo de objeto, si no hay nada, devolvera una lista vacia
    @Override
    public Optional<List<Purchase>> getPurchaseByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra c = purchaseMapper.toCompra(purchase);
        c.getProductos().forEach(comprasProducto -> comprasProducto.setCompra(c));

        return purchaseMapper.toPurchase(compraCrudRepository.save(c));
    }
}
