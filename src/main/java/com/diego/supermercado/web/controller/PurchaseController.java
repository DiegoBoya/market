package com.diego.supermercado.web.controller;

import com.diego.supermercado.domain.dto.Purchase;
import com.diego.supermercado.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/all")
    @ApiOperation("Get all market purchases")
    public ResponseEntity<List<Purchase>> getAllPurchases(){

        try{
           List<Purchase> purchases =  purchaseServiceImpl.getAllPurchases();
            return new ResponseEntity<>(purchases,HttpStatus.OK);
        }catch(Exception e){
            logger.info("Ocurrio un error al intentar recuperar las compras.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    @ApiOperation("Get all market purchases for the specified Client")
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
    @ApiOperation("Save a new purchase in the database")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase){

        ResponseEntity<Purchase> response = null;

        try{
            Purchase p = purchaseServiceImpl.save(purchase);
            response = new ResponseEntity<>(p, HttpStatus.CREATED);
        }catch(Exception e){
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
            logger.info("error al intentar guardar la Compra");
        }
        return response;
    }
}
