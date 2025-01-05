package com.serviceTest;

import com.dao.UserDAO;
import com.model.Role;
import com.model.User;
import com.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User("testUser", "testPassword", null);

        // Act
        userService.saveUser(user);

        // Assert
        verify(userDAO, times(1)).save(user);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User("user1", "password1", null);
        User user2 = new User("user2", "password2", null);
        List<User> users = Arrays.asList(user1, user2);
        when(userDAO.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userDAO, times(1)).findAll();
    }

    @Test
    void testFindById_True() {
        // Arrange
        long id = 1L;
        when(userDAO.findById(id)).thenReturn(Optional.of(new User()));

        // Act
        boolean exists = userService.findById(id);

        // Assert
        assertTrue(exists);
        verify(userDAO, times(1)).findById(id);
    }

    @Test
    void testFindById_False() {
        // Arrange
        long id = 1L;
        when(userDAO.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean exists = userService.findById(id);

        // Assert
        assertFalse(exists);
        verify(userDAO, times(1)).findById(id);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        long id = 1L;

        // Act
        userService.deleteUser(id);

        // Assert
        verify(userDAO, times(1)).deleteById(id);
    }

    @Test
    void testGetUserById_Success() {
        // Arrange
        long id = 1L;
        User user = new User("testUser", "testPassword", null);
        when(userDAO.findById(id)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(id);

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(userDAO, times(1)).findById(id);
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        long id = 1L;
        when(userDAO.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> userService.getUserById(id));
        verify(userDAO, times(1)).findById(id);
    }
}