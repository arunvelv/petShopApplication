package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Transactions;
import com.model.Transactions.TransactionStatus;

@Repository
public interface TransactionsDAO extends JpaRepository<Transactions, Integer> {

    List<Transactions> findByTransactionStatus(TransactionStatus status);

    @Query("SELECT t FROM Transactions t WHERE t.customers.customerId = :customerId")
    List<Transactions> findByCustomers_CustomerId(int customerId);

}
