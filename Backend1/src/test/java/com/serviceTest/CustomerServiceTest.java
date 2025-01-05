package com.serviceTest;
 
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.dao.AddressDAO;
import com.dao.CustomerDAO;
import com.model.Address;
import com.model.Customer;
import com.model.Transactions.TransactionStatus;
import com.service.CustomerService;
 
class CustomerServiceTest {
 
    @InjectMocks
    private CustomerService customerService;
 
    @Mock
    private CustomerDAO customerDAO;
 
    @Mock
    private AddressDAO addressDAO;
 
    private Customer customer;
    private Address address;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address = new Address(1, "Street1", "City1", "State1", "12345");
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress(address);
    }
 
    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerDAO.findAll()).thenReturn(customers);
 
        List<Customer> result = customerService.getAllCustomers();
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customer.getFirstName(), result.get(0).getFirstName());
        verify(customerDAO, times(1)).findAll();
    }
 
    @Test
    void testGetCustomerById() {
        when(customerDAO.findByCustomerId(1)).thenReturn(customer);
 
        Customer result = customerService.getCustomerById(1);
 
        assertNotNull(result);
        assertEquals(customer.getFirstName(), result.getFirstName());
        verify(customerDAO, times(1)).findByCustomerId(1);
    }
 
    @Test
    void testGetCustomerByName() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerDAO.findByFirstNameAndLastName("John", "Doe")).thenReturn(customers);
 
        List<Customer> result = customerService.getCustomerByName("John", "Doe");
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customer.getLastName(), result.get(0).getLastName());
        verify(customerDAO, times(1)).findByFirstNameAndLastName("John", "Doe");
    }
 
    @Test
    void testGetCustomersByCity() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerDAO.findByAddressCity("City1")).thenReturn(customers);
 
        List<Customer> result = customerService.getCustomersByCity("City1");
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customer.getAddress().getCity(), result.get(0).getAddress().getCity());
        verify(customerDAO, times(1)).findByAddressCity("City1");
    }
 
    @Test
    void testSaveCustomer() {
        // Set address ID to 0 to ensure the save method is called
        address.setAddressId(0); // This will trigger the save on addressDAO
        when(addressDAO.save(address)).thenReturn(address); // Mock save of address
        when(customerDAO.save(customer)).thenReturn(customer); // Mock save of customer
 
        Customer savedCustomer = customerService.saveCustomer(customer, address);
 
        assertNotNull(savedCustomer);
        assertEquals(1, savedCustomer.getCustomerId()); // Assuming the customer ID gets set
        verify(addressDAO, times(1)).save(address); // Ensure addressDAO.save() was called
        verify(customerDAO, times(1)).save(customer); // Ensure customerDAO.save() was called
    }

 
    @Test
    void testUpdateCustomer() {
        when(customerDAO.findByCustomerId(1)).thenReturn(customer);
        when(customerDAO.save(customer)).thenReturn(customer);
 
        Customer updatedDetails = new Customer();
        updatedDetails.setFirstName("Jane");
        updatedDetails.setLastName("Smith");
        updatedDetails.setEmail("jane.smith@example.com");
        updatedDetails.setPhoneNumber("0987654321");
        updatedDetails.setAddress(address);
 
        Customer result = customerService.updateCustomer(1, updatedDetails);
 
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        verify(customerDAO, times(1)).findByCustomerId(1);
        verify(customerDAO, times(1)).save(customer);
    }
 
    @Test
    void testGetCustomersByState() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerDAO.findByAddressState("State1")).thenReturn(customers);
 
        List<Customer> result = customerService.getCustomersByState("State1");
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customer.getAddress().getState(), result.get(0).getAddress().getState());
        verify(customerDAO, times(1)).findByAddressState("State1");
    }
}
 
 