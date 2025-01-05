package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Role;
import com.model.User;

import java.util.ArrayList;
import java.util.List;

class UserTest {

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        // Initialize Role object (you can add more roles if needed)
        role = new Role("ADMIN");

        // Initialize User object
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user = new User("testUser", "password123", roles);
    }

    @Test
    void testUserDefaultConstructor() {
        User defaultUser = new User();

        assertNull(defaultUser.getUsername());
        assertNull(defaultUser.getPassword());
        assertNull(defaultUser.getRoles());
    }

    @Test
    void testUserConstructor() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        User newUser = new User("newUser", "newPassword123", roles);

        assertEquals("newUser", newUser.getUsername());
        assertEquals("newPassword123", newUser.getPassword());
        assertEquals(1, newUser.getRoles().size());
        assertEquals("USER", newUser.getRoles().get(0).getRole_name());
    }

    @Test
    void testGettersAndSetters() {
        user.setUsername("updatedUser");
        user.setPassword("newPassword456");

        List<Role> updatedRoles = new ArrayList<>();
        updatedRoles.add(new Role("MODERATOR"));
        user.setRoles(updatedRoles);

        assertEquals("updatedUser", user.getUsername());
        assertEquals("newPassword456", user.getPassword());
        assertEquals(1, user.getRoles().size());
        assertEquals("MODERATOR", user.getRoles().get(0).getRole_name());
    }

    @Test
    void testRoleAssignment() {
        Role newRole = new Role("USER");
        List<Role> updatedRoles = new ArrayList<>();
        updatedRoles.add(newRole);

        user.setRoles(updatedRoles);

        assertEquals(1, user.getRoles().size());
        assertEquals("USER", user.getRoles().get(0).getRole_name());
    }
}