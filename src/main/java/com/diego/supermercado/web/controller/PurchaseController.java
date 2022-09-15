package com.diego.supermercado.web.controller;

import com.diego.supermercado.domain.Purchase;
import com.diego.supermercado.domain.service.PurchaseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private static final Logger logger = Logger.getLogger(PurchaseController.class.getName());

    @Autowired
    private PurchaseService purchaseServiceImpl;

    @RequestMapping("/all")
    public ResponseEntity<List<Purchase>> getAllPurchases(){

        try{
           List<Purchase> purchases =  purchaseServiceImpl.getAllPurchases();
            return new ResponseEntity<>(purchases,HttpStatus.OK);
        }catch(Exception e){
            logger.info("Ocurrio un error al intentar recuperar las compras.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getClientPurchases(@PathVariable String clientId){
        //todo: si el cliente no existe tira el 204... deberia validarse
        try{
            Optional<List<Purchase>> optionalList = purchaseServiceImpl.getClientPurchases(clientId);
            if(optionalList.isPresent() && !optionalList.get().isEmpty()) {
                return new ResponseEntity<>(optionalList.get(), HttpStatus.OK);
            } else {
                logger.info("el cliente no tiene compras");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            logger.info("Ocurrio un error al buscar al cliente "+ clientId + " en la BD.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase){

        ResponseEntity<Purchase> response = null;

        try{
            purchaseServiceImpl.save(purchase);
            response = new ResponseEntity<>(purchase, HttpStatus.CREATED);
        }catch(Exception e){
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
            logger.info("error al intentar guardar la Compra");
        }
        return response;
    }
}
