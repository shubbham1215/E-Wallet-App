package com.ewallet.TransactionManagementSystem.repository;

import com.ewallet.TransactionManagementSystem.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    Optional<Transaction> findByTransactionId(String id);
}
