package com.serviceTest;

import com.dao.RoleDAO;
import com.model.Role;
import com.service.RoleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleDAO roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByRoleName_Success() {
        // Arrange
        String roleName = "ROLE_USER";
        Role role = new Role(roleName);
        when(roleRepository.findByRoleName(roleName)).thenReturn(role);

        // Act
        Role foundRole = roleService.findByRoleName(roleName);

        // Assert
        assertNotNull(foundRole);
        assertEquals(roleName, foundRole.getRole_name());
        verify(roleRepository, times(1)).findByRoleName(roleName);
    }

    @Test
    void testFindByRoleName_NotFound() {
        // Arrange
        String roleName = "ROLE_ADMIN";
        when(roleRepository.findByRoleName(roleName)).thenReturn(null);

        // Act
        Role foundRole = roleService.findByRoleName(roleName);

        // Assert
        assertNull(foundRole);
        verify(roleRepository, times(1)).findByRoleName(roleName);
    }

    @Test
    void testSaveRole() {
        // Arrange
        Role role = new Role("ROLE_USER");

        // Act
        roleService.saveRole(role);

        // Assert
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testExistsByRoleName_True() {
        // Arrange
        String roleName = "ROLE_USER";
        when(roleRepository.findByRoleName(roleName)).thenReturn(new Role(roleName));

        // Act
        boolean exists = roleService.existsByRoleName(roleName);

        // Assert
        assertTrue(exists);
        verify(roleRepository, times(1)).findByRoleName(roleName);
    }

    @Test
    void testExistsByRoleName_False() {
        // Arrange
        String roleName = "ROLE_ADMIN";
        when(roleRepository.findByRoleName(roleName)).thenReturn(null);

        // Act
        boolean exists = roleService.existsByRoleName(roleName);

        // Assert
        assertFalse(exists);
        verify(roleRepository, times(1)).findByRoleName(roleName);
    }
}

