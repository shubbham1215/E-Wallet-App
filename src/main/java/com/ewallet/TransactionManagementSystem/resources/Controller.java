package com.ewallet.TransactionManagementSystem.resources;

import com.ewallet.TransactionManagementSystem.manager.TransactionManager;
import com.ewallet.TransactionManagementSystem.model.TransactionRequest;
import com.ewallet.TransactionManagementSystem.model.TransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    @Autowired
    private TransactionManager transactionManager;


    @PostMapping("/transaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transactionResponse
                = null;
        try {
            transactionResponse = transactionManager.createTransaction(transactionRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.accepted().body(transactionResponse);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity getTransaction(@PathVariable("id") String id){
        TransactionResponse transactionResponse
                = null;
        try {
            transactionResponse = transactionManager.getTransaction(id);
            return ResponseEntity.ok(transactionResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
