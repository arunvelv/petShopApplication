package com.controllerTest;

import com.controller.*;
import com.model.Role;
import com.model.User;
import com.service.RoleService;
import com.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        List<User> mockUsers = new ArrayList<>();
        User user = new User("testUser", "password", new ArrayList<>());
        mockUsers.add(user);

        when(userService.getAllUsers()).thenReturn(mockUsers);

        // Act
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        User mockUser = new User("testUser", "password", new ArrayList<>());
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        doNothing().when(userService).saveUser(any(User.class));

        // Act
        ResponseEntity<?> response = userController.registerUser(mockUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(passwordEncoder, times(1)).encode("password");
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testRegisterUser_Failure() {
        // Arrange
        User mockUser = new User("testUser", "password", new ArrayList<>());
        doThrow(new RuntimeException("DB Error")).when(userService).saveUser(any(User.class));

        // Act
        ResponseEntity<?> response = userController.registerUser(mockUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testRegisterAdmin_Success() {
        // Arrange
        User mockUser = new User("adminUser", "password", new ArrayList<>());
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        doNothing().when(userService).saveUser(any(User.class));

        // Act
        ResponseEntity<?> response = userController.registerAdmin(mockUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(passwordEncoder, times(1)).encode("password");
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testRegisterAdmin_Failure() {
        // Arrange
        User mockUser = new User("adminUser", "password", new ArrayList<>());
        doThrow(new RuntimeException("DB Error")).when(userService).saveUser(any(User.class));

        // Act
        ResponseEntity<?> response = userController.registerAdmin(mockUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(userService, times(1)).saveUser(any(User.class));
    }
}