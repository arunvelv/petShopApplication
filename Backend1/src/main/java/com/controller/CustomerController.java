package com.controller;

import com.exception.InvalidInputException;
import com.exception.Response;
import com.model.*;
import com.model.Transactions.TransactionStatus;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{first_name}/{last_name}")
    public ResponseEntity<List<Customer>> getCustomerByName(
            @PathVariable("first_name") String firstName, 
            @PathVariable("last_name") String lastName) {
        List<Customer> customers = customerService.getCustomerByName(firstName, lastName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }



    @GetMapping("/city/{city}")
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable String city) {
        List<Customer> customers = customerService.getCustomersByCity(city);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Customer>> getCustomersByState(@PathVariable String state) {
        List<Customer> customers = customerService.getCustomersByState(state);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    
    @GetMapping("/transactions/{id}")
    public ResponseEntity<List<Customer>> getTransactionsByCustomerId(@PathVariable("id") int customerId) {
        List<Customer> customers = customerService.getTransactionsByCustomerId(customerId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @GetMapping("/transactions-status/{status}")
    public ResponseEntity<List<Customer>> getCustomersByTransactionStatus(@PathVariable TransactionStatus status) {
        List<Customer> customers = customerService.getCustomersByTransactionStatus(status);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/no-transactions")
    public ResponseEntity<List<Customer>> getCustomersWithNoTransactions() {
        List<Customer> customers = customerService.getCustomersWithNoTransactions();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<List<Pets>> getPetsByCustomerId(@PathVariable("id") int customerId) {
        List<Pets> pets = customerService.getPetsByCustomerId(customerId);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

//   @PostMapping("/add")
//   public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
//        Customer createdCustomer = customerService.addCustomer(customer);
//       return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//       }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int customerId, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerPayload payload) {
    	Address address = payload.getAddress();
        Customer customer = payload.getCustomer();
          if (address == null || customer == null) {
            throw new InvalidInputException("VALIDATION_ERROR");
       }
       List<Customer> existingCustomers = customerService.findByAddress(address.getAddressId());
        if (!existingCustomers.isEmpty()) {
           throw new InvalidInputException("ADD_FAILS");
       }
       Customer createdCustomer = customerService.saveCustomer(customer, address);
           return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}
