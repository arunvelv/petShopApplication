package com.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.dao.CustomerDAO;
import com.dao.TransactionsDAO;
import com.model.Transactions;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsDAO transactionsDAO;

    @Autowired
    private CustomerDAO customerDAO;

    public void saveTransaction(Transactions transaction) {
        int customerId = transaction.getCustomer().getCustomerId();

        // Validate that the customer exists
        if (!customerDAO.existsById(customerId)) {
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);
        }

        transactionsDAO.save(transaction);
    }

    public Transactions getTransactionById(int transactionId) {
        return transactionsDAO.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + transactionId));
    }

    public List<Transactions> getSuccessTransactions() {
        return transactionsDAO.findByTransactionStatus(Transactions.TransactionStatus.SUCCESS);
    }

    public List<Transactions> getFailedTransactions() {
        return transactionsDAO.findByTransactionStatus(Transactions.TransactionStatus.FAILED);
    }

    public List<Transactions> getTransactionsByCustomerId(int customerId) {
        return transactionsDAO.findByCustomers_CustomerId(customerId);
    }

    public List<Transactions> getAllTransactions() {
        return transactionsDAO.findAll();
    }
}
