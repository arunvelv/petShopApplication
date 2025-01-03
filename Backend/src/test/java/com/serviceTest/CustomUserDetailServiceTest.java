package com.serviceTest;

import com.dao.UserDAO;
import com.model.Role;
import com.model.User;
import com.service.CustomUserDetailService;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private CustomUserDetailService customUserDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_Success() {
        // Arrange
        String username = "testUser";
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole_name("ROLE_USER");
        roles.add(role);

        User user = new User(username, "password", roles);

        // Mock the DAO response
        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        
        // Verify the interactions
        verify(userDAO, times(1)).findByUsername(username);
    }


    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "unknownUser";
        when(userDAO.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailService.loadUserByUsername(username));
        verify(userDAO, times(1)).findByUsername(username);
    }
}

