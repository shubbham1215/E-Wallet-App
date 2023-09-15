package com.ewallet.TransactionManagementSystem.manager;

import com.ewallet.TransactionManagementSystem.model.TransactionRequest;
import com.ewallet.TransactionManagementSystem.model.TransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import javassist.NotFoundException;

public interface TransactionManager {
    TransactionResponse createTransaction(TransactionRequest transactionRequest) throws JsonProcessingException;

    TransactionResponse getTransaction(String id) throws NotFoundException;

    void updateTransaction(String updateRequest) throws JsonProcessingException;
}
