package com.dao;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Address;
 
@Repository
public interface AddressDAO extends JpaRepository<Address, Integer> {
    List<Address> findByAddressId(int addressId);
}