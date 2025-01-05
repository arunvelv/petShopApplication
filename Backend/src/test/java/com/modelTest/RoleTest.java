package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Role;
import com.model.User;

class RoleTest {

    private Role role;
    private User user;

    @BeforeEach
    void setUp() {
        // Initialize a User instance for the Role's ManyToOne relationship
        user = new User(); // Assuming User is another class with default constructor
        user.setUsername("testUser");
        
        // Initialize the Role object with a role_name
        role = new Role("ADMIN");
        role.setUser(user);
    }

    @Test
    void testRoleDefaultConstructor() {
        Role defaultRole = new Role();
        
        assertNull(defaultRole.getRole_name());
        assertNull(defaultRole.getUser());
    }

    @Test
    void testRoleConstructor() {
        Role customRole = new Role("USER");
        
        assertEquals("USER", customRole.getRole_name());
        assertNull(customRole.getUser());
    }

    @Test
    void testGettersAndSetters() {
        role.setRole_name("MODERATOR");
        
        assertEquals("MODERATOR", role.getRole_name());
        assertEquals(user, role.getUser());
    }

    @Test
    void testUserAssignment() {
        User newUser = new User(); // Assuming a constructor for User with a username property
        newUser.setUsername("newUser");
        
        role.setUser(newUser);
        
        assertEquals("newUser", role.getUser().getUsername());
    }
}