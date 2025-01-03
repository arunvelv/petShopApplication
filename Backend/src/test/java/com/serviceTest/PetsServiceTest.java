package com.serviceTest;


import com.dao.PetsDAO;
import com.model.PetCategories;
import com.model.Pets;
import com.service.PetsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
 
class PetsServiceTest {
 
    @Mock

    private PetsDAO petsDAO;
 
    @InjectMocks

    private PetsService petsService;
 
    @BeforeEach

    void setUp() {

        initMocks(this);

    }
 
    @Test

    void testGetAllPets() {

        List<Pets> mockPetsList = new ArrayList<>();

        mockPetsList.add(new Pets(0, "Buddy", "Golden Retriever", 2, 30000.0f, "Friendly dog", "url1", null, null, null, null, null, null, null));

        mockPetsList.add(new Pets(0, "Mittens", "Persian Cat", 1, 20000.0f, "Adorable cat", "url2", null, null, null, null, null, null, null));
 
        when(petsDAO.findAll()).thenReturn(mockPetsList);
 
        ResponseEntity<?> response = petsService.getAllPets();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockPetsList, response.getBody());
 
        verify(petsDAO, times(1)).findAll();

    }
 
    @Test

    void testGetPetById_Found() {

        Pets mockPet = new Pets(0, "Buddy", "Golden Retriever", 2, 30000.0f, "Friendly dog", "url1", null, null, null, null, null, null, null);

        when(petsDAO.findById(1)).thenReturn(Optional.of(mockPet));
 
        ResponseEntity<?> response = petsService.getPetById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockPet, response.getBody());
 
        verify(petsDAO, times(1)).findById(1);

    }
 
    @Test

    void testGetPetById_NotFound() {

        when(petsDAO.findById(1)).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = petsService.getPetById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("Pet not found", response.getBody());
 
        verify(petsDAO, times(1)).findById(1);

    }
 
    @Test

    void testAddPet() {

        Pets newPet = new Pets(0, "Buddy", "Golden Retriever", 2, 30000.0f, "Friendly dog", "url1", null, null, null, null, null, null, null);

        when(petsDAO.save(newPet)).thenReturn(newPet);
 
        ResponseEntity<?> response = petsService.addPet(newPet);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(newPet, response.getBody());
 
        verify(petsDAO, times(1)).save(newPet);

    }
 
    @Test

    void testUpdatePet_Found() {

        Pets existingPet = new Pets(0, "Buddy", "Golden Retriever", 2, 30000.0f, "Friendly dog", "url1", null, null, null, null, null, null, null);

        Pets updatedPet = new Pets(0, "Buddy Updated", "Golden Retriever", 3, 35000.0f, "Friendly dog updated", "url2", null, null, null, null, null, null, null);
 
        when(petsDAO.findById(1)).thenReturn(Optional.of(existingPet));

        when(petsDAO.save(any(Pets.class))).thenReturn(updatedPet);
 
        ResponseEntity<?> response = petsService.updatePet(1, updatedPet);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(updatedPet, response.getBody());
 
        verify(petsDAO, times(1)).findById(1);

        verify(petsDAO, times(1)).save(any(Pets.class));

    }
 
    @Test

    void testUpdatePet_NotFound() {

        Pets updatedPet = new Pets(0, "Buddy Updated", "Golden Retriever", 3, 35000.0f, "Friendly dog updated", "url2", null, null, null, null, null, null, null);
 
        when(petsDAO.findById(1)).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = petsService.updatePet(1, updatedPet);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("Pet not found", response.getBody());
 
        verify(petsDAO, times(1)).findById(1);

        verify(petsDAO, never()).save(any(Pets.class));

    }
 
    @Test

    void testGetPetsByCategory() {

        List<Pets> mockPetsList = new ArrayList<>();

        mockPetsList.add(new Pets(0, "Buddy", "Golden Retriever", 2, 30000.0f, "Friendly dog", "url1", null, null, null, null, null, null, null));
 
        when(petsDAO.findByCategoryName("Dogs")).thenReturn(mockPetsList);
 
        ResponseEntity<?> response = petsService.getPetsByCategory("Dogs");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockPetsList, response.getBody());
 
        verify(petsDAO, times(1)).findByCategoryName("Dogs");

    }

}

 