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
    private AddressDAO addressDAO;
    

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
		return customerDAO.findByAddress_AddressId(addressId);
    	
    }

    public Customer addCustomer(Customer customer) {
        return customerDAO.save(customer);
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

    public void saveCustomerAndAddress(Customer customer, Address address) {
        // Save the address first (if needed)
        if (address.getAddressId() == 0) {
            addressDAO.save(address);
        }

        // Associate the saved address with the customer
        customer.setAddress(address);
        customerDAO.save(customer);
    }


}
