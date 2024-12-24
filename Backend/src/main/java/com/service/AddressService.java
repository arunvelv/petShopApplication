package com.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dao.AddressDAO;
import com.model.Address;
 
@Service
public class AddressService {
    @Autowired
    private AddressDAO addressDAO;
 
    public ResponseEntity<String> addAddress(Address address) {
        addressDAO.save(address);
        return new ResponseEntity<>("Address added successfully", HttpStatus.CREATED);
    }
 
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addressList = addressDAO.findAll();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
 
    public ResponseEntity<String> updateAddress(Address address) {
        Optional<Address> existingAddress = addressDAO.findById(address.getAddressId());
        if (existingAddress.isPresent()) {
            addressDAO.save(address);
            return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }
    }
 
    public ResponseEntity<List<Address>> findAddressByAddressId(int addressId) {
        List<Address> addresses = addressDAO.findByAddressId(addressId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
 
 