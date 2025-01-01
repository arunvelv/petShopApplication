package com.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.Address;
import com.service.AddressService;
 
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/address")
public class AddressController {
 
    @Autowired
    private AddressService addressService;
 
    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }
 
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return addressService.getAllAddresses();
    }
 
    @PutMapping("/update/{addressId:[0-9]+}")
    public ResponseEntity<Address> updateAddress(@PathVariable int addressId, @RequestBody Address address) {
        address.setAddressId(addressId);
        return addressService.updateAddress(address);
    }
 
    @GetMapping("/{addressId:[0-9]+}")
    public ResponseEntity<List<Address>> findAddressByAddressId(@PathVariable int addressId) {
        return addressService.findAddressByAddressId(addressId);
    }
}
 
 
 