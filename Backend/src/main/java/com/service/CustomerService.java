package com.service;

import com.dao.*;
import com.model.*;
import com.model.Transactions.TransactionStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    
    @Autowired
    private AddressDAO	addressDAO;
    

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomerById(int customerId) {
        return customerDAO.findByCustomerId(customerId);
    }

    public List<Customer> getCustomerByName(String firstName, String lastName) {
        return customerDAO.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Customer> getCustomersByCity(String city) {
        return customerDAO.findByAddressCity(city);
    }

    public List<Customer> getCustomersByState(String state) {
        return customerDAO.findByAddressState(state);
    }

    public List<Customer> getTransactionsByCustomerId(int customerId) {
        return customerDAO.findTransactionsByCustomerId(customerId);
    }

    public List<Customer> getCustomersByTransactionStatus(TransactionStatus status) {
        return customerDAO.findCustomersByTransactionStatus(status);
    }

    public List<Customer> getCustomersWithNoTransactions() {
        return customerDAO.findByTransactionsIsEmpty();
    }

    public List<Pets> getPetsByCustomerId(int customerId) {
        return customerDAO.findPetsByCustomerId(customerId);
    }
    
    public List<Customer> findByAddress(int addressId) {
		return customerDAO.findByAddressAddressId(addressId);
    	
    }

    public Customer addCustomer(Customer customers) {
        return customerDAO.save(customers);
    }

    public Customer updateCustomer(int customerId, Customer customerDetails) {
        Customer customer = customerDAO.findByCustomerId(customerId);
        if (customer != null) {
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setAddress(customerDetails.getAddress());
            return customerDAO.save(customer);
        }
        return null;
    }

    public Customer saveCustomer(Customer customers, Address address) {
        if (address.getAddressId() == 0) {
            addressDAO.save(address);
        }
        customers.setAddress(address);
        return customerDAO.save(customers);
    }

}
