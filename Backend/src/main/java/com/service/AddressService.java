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
    
    @Autowired
    private AddressDAO addressdao;

    public Address save(Address address) {
        return addressdao.save(address);
    }
 
    public ResponseEntity<Address> addAddress(Address address) {
        addressDAO.save(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addressList = addressDAO.findAll();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
 
    public ResponseEntity<Address> updateAddress(Address address) {
        Optional<Address> existingAddress = addressDAO.findById(address.getAddressId());
        if (existingAddress.isPresent()) {
            addressDAO.save(address);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(address, HttpStatus.NOT_FOUND);
        }
    }
 
    public ResponseEntity<List<Address>> findAddressByAddressId(int addressId) {
        List<Address> addresses = addressDAO.findByAddressId(addressId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
 
 