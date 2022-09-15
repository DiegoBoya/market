package com.diego.supermercado.domain.service;

import com.diego.supermercado.domain.Purchase;
import com.diego.supermercado.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PurchaseService {

    private static Logger logger = Logger.getLogger(PurchaseService.class.getName());

    @Autowired
    private PurchaseRepository purchaseRepositoryImpl;

    public List<Purchase> getAllPurchases(){
        return purchaseRepositoryImpl.getAll();
    }

    public Optional<List<Purchase>> getClientPurchases(String clientId){
        return purchaseRepositoryImpl.getPurchaseByClient(clientId);
    }

    public Purchase save(Purchase purchase){
        return purchaseRepositoryImpl.save(purchase);
    }
}
