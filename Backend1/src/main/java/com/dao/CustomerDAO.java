package com.dao;

import com.model.*;
import com.model.Transactions.TransactionStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    List<Customer> findAll();
    
    Customer findByCustomerId(int customerId);
    
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByAddressCity(String city);
    
    List<Customer> findByAddressState(String state);
    
    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId")
    List<Customer> findTransactionsByCustomerId(@Param("customerId") int customerId);
    
    @Query("SELECT c FROM Customer c JOIN c.transactions t WHERE t.transactionStatus = :status")
    List<Customer> findCustomersByTransactionStatus(@Param("status") TransactionStatus status);

    
    List<Customer> findByTransactionsIsEmpty();
    
    @Query("SELECT p FROM Pets p JOIN p.transactions t WHERE t.customers.customerId = :customerId")
    List<Pets> findPetsByCustomerId(@Param("customerId") int customerId);

	List<Customer> findByAddressAddressId(int addressId);

}
