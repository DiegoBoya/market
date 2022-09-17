package com.diego.supermercado.domain.repository;

import com.diego.supermercado.domain.dto.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getPurchaseByClient(String clientId);
    Purchase save(Purchase purchase);

}
