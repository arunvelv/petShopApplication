package com.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.AddressDAO;
import com.dao.SuppliersDAO;
import com.exception.InvalidInputException;
import com.model.Address;
import com.model.Customer;
import com.model.Employee;
import com.model.Suppliers;
 
@Service
public class SuppliersService {
 
    @Autowired
    private SuppliersDAO suppliersDAO;
    
    @Autowired
    private AddressDAO addressDAO;
    
 
    public List<Suppliers> getAllSuppliers() {
        return suppliersDAO.findAll();
    }
    
    public Suppliers addSuppliers(Suppliers suppliers) {
        return suppliersDAO.save(suppliers);
    }
 
    public Suppliers getSuppliersById(int suppliersId) {
        return suppliersDAO.findBySuppliersId(suppliersId);
    }
 
    public List<Suppliers> findSuppliersByName(String name) {
        return suppliersDAO.findByName(name);
    }
 
    public ResponseEntity<?> findSupplierByCity(String city) {
        List<Suppliers> supplierList = suppliersDAO.findByCity(city);
        return new ResponseEntity<List<Suppliers>>(supplierList, HttpStatus.OK);
    }
 
    public ResponseEntity<?> findSupplierByState(String state) {
        List<Suppliers> supplierList = suppliersDAO.findByState(state);
        return new ResponseEntity<List<Suppliers>>(supplierList, HttpStatus.OK);
    }
    
    public List<Suppliers> findByAddress(int addressId) {
		return suppliersDAO.findByAddressAddressId(addressId);
    	
    }
 
    public Suppliers saveSuppliers(Suppliers suppliers, Address address) {
    	
        if (address.getAddressId() == 0) {
            addressDAO.save(address); 
        }
        suppliers.setAddress(address);
        return suppliersDAO.save(suppliers);
    }
    
   

   

    
    public Suppliers updateSuppliers(int suppliersId,Suppliers suppliersDetails) {
    	Suppliers suppliers = suppliersDAO.findBySuppliersId(suppliersId);
        if (suppliers != null) {
        	suppliers.setName(suppliersDetails.getName());
        	suppliers.setContactPerson(suppliersDetails.getContactPerson());
        	suppliers.setPhoneNumber(suppliersDetails.getPhoneNumber());
        	suppliers.setEmail(suppliersDetails.getEmail());
        	suppliers.setAddress(suppliersDetails.getAddress());
            return suppliersDAO.save(suppliers);
        }
        return null;
    }
 
}
 