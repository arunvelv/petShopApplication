package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.AuthenticateUser;

class AuthenticateUserTest {

    private AuthenticateUser authenticateUser;

    @BeforeEach
    void setUp() {
        authenticateUser = new AuthenticateUser();
        authenticateUser.setUsername("testUser");
        authenticateUser.setPassword("testPassword");
        authenticateUser.setRole("ADMIN");
    }

    @Test
    void testAuthenticateUserDefaultConstructor() {
        AuthenticateUser defaultUser = new AuthenticateUser();

        assertNull(defaultUser.getUsername());
        assertNull(defaultUser.getPassword());
        assertNull(defaultUser.getRole());
    }

    @Test
    void testAuthenticateUserGetters() {
        assertEquals("testUser", authenticateUser.getUsername());
        assertEquals("testPassword", authenticateUser.getPassword());
        assertEquals("ADMIN", authenticateUser.getRole());
    }

    @Test
    void testAuthenticateUserSetters() {
        authenticateUser.setUsername("newUser");
        authenticateUser.setPassword("newPassword");
        authenticateUser.setRole("USER");

        assertEquals("newUser", authenticateUser.getUsername());
        assertEquals("newPassword", authenticateUser.getPassword());
        assertEquals("USER", authenticateUser.getRole());
    }
}
