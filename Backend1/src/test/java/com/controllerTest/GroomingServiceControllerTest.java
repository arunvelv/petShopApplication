package com.controllerTest;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import com.controller.GroomingServiceController;
import com.model.GroomingService;
import com.service.GroomingServiceService;
 
class GroomingServiceControllerTest {
 
    @Mock
    private GroomingServiceService groomingServiceService;
 
    @InjectMocks
    private GroomingServiceController groomingServiceController;
 
    GroomingServiceControllerTest() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddGroomingService() {
        GroomingService service = new GroomingService();
        service.setName("Premium Grooming");
 
        ResponseEntity<String> mockResponse = ResponseEntity.status(201).body("Grooming service added successfully");
        doReturn(mockResponse).when(groomingServiceService).addGroomingService(service);
 
        ResponseEntity<?> response = groomingServiceController.addGroomingService(service);
 
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Grooming service added successfully", response.getBody());
    }
 
    @Test
    void testGetAllGroomingServices() {
        List<GroomingService> mockServices = List.of(new GroomingService(), new GroomingService());
        ResponseEntity<List<GroomingService>> mockResponse = ResponseEntity.ok(mockServices);
 
        doReturn(mockResponse).when(groomingServiceService).getAllGroomingServices();
 
        ResponseEntity<?> response = groomingServiceController.getAllGroomingServices();
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testFindByServiceId() {
        // Arrange
        int serviceId = 1;
        GroomingService mockService = new GroomingService();
        mockService.setServiceId(serviceId);
        mockService.setName("Full Grooming");
        mockService.setDescription("Complete grooming package for pets");
        mockService.setPrice(50.0f);
        mockService.setAvailable(true);

        ResponseEntity<GroomingService> mockResponse = ResponseEntity.ok(mockService);

        // Use doReturn instead of when-thenReturn to avoid type mismatch issues
        doReturn(mockResponse).when(groomingServiceService).findByServiceId(serviceId);

        // Act
        ResponseEntity<?> response = groomingServiceController.findByServiceId(serviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof GroomingService);

        GroomingService returnedService = (GroomingService) response.getBody();
        assertEquals(serviceId, returnedService.getServiceId());
        assertEquals("Full Grooming", returnedService.getName());
        assertEquals("Complete grooming package for pets", returnedService.getDescription());
        assertEquals(50.0f, returnedService.getPrice());
        assertTrue(returnedService.isAvailable());
    }
 
    @Test
    void testGetByAvailable() {
        List<GroomingService> mockServices = List.of(new GroomingService());
        ResponseEntity<List<GroomingService>> mockResponse = ResponseEntity.ok(mockServices);
 
        doReturn(mockResponse).when(groomingServiceService).findByAvailable(true);
 
        ResponseEntity<?> response = groomingServiceController.getByAvailable();
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testUpdateService() {
        GroomingService updatedService = new GroomingService();
        updatedService.setName("Updated Service");
 
        ResponseEntity<String> mockResponse = ResponseEntity.ok("Grooming service updated successfully");
        doReturn(mockResponse).when(groomingServiceService).updateGroomingService(1, updatedService);
 
        ResponseEntity<?> response = groomingServiceController.updateService(1, updatedService);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Grooming service updated successfully", response.getBody());
    }
}
 