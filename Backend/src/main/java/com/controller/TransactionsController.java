package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Pets;
import com.model.Transactions;
import com.service.TransactionsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/transaction_history")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody Transactions transaction) {
        transactionsService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction added successfully.");
    }

    @GetMapping("/{transaction_id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable int transaction_id) {
        try {
            Transactions transaction = transactionsService.getTransactionById(transaction_id);
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/successful")
    public ResponseEntity<List<Transactions>> getSuccessfulTransactions() {
        List<Transactions> transactions = transactionsService.getSuccessTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/failed")
    public ResponseEntity<List<Transactions>> getFailedTransactions() {
        List<Transactions> transactions = transactionsService.getFailedTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/by_customer/{customer_id}")
    public ResponseEntity<List<Transactions>> getTransactionsByCustomerId(@PathVariable int customer_id) {
        List<Transactions> transactions = transactionsService.getTransactionsByCustomerId(customer_id);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
