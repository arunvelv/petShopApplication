//package com.controllerTest;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//
//import com.controller.AuthController;
//import com.dao.UserDAO;
//import com.exception.Response;
//import com.filter.JwtResponse;
//import com.filter.JwtToken;
//import com.model.AuthenticateUser;
//import com.model.Role;
//import com.model.User;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class AuthControllerTest {
//
//    @InjectMocks
//    private AuthController authController;
//
//    @Mock
//    private DaoAuthenticationProvider provider;
//
//    @Mock
//    private UserDAO userRepository;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private JwtToken jwtToken;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAuthenticateSuccess() {
//        // Arrange: Prepare user details for authentication
//        AuthenticateUser authUser = new AuthenticateUser();
//        authUser.setUsername("testuser");
//        authUser.setPassword("password");
//        authUser.setRole("ROLE_USER");
//
//        // Create a mock user to return from the repository
//        User mockUser = new User();
//        mockUser.setUsername("testuser");
//        mockUser.setRoles(Collections.singletonList(new Role("ROLE_USER")));
//
//        // Mock AuthenticationManager to return a valid authentication object
//        Authentication mockAuthentication = mock(Authentication.class);
//        when(mockAuthentication.isAuthenticated()).thenReturn(true);
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenReturn(mockAuthentication);  // Mocking the authentication manager
//
//        // Mock UserRepository to return the mock user when searched by username
//        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
//
//        // Mock JwtToken to return a token
//        when(jwtToken.generateToken("testuser", "password", "ROLE_USER")).thenReturn("mock-token");
//
//        // Act: Call the authenticate method of the controller
//        ResponseEntity<?> response = authController.authenticate(authUser);
//
//        // Assert: Verify the response and that the mock methods were called
//        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//        assertTrue(response.getBody() instanceof JwtResponse);
//        JwtResponse jwtResponse = (JwtResponse) response.getBody();
//        assertEquals("mock-token", jwtResponse.getToken());
//
//        // Verify that authenticationManager.authenticate was called with the right token
//        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        verify(userRepository).findByUsername("testuser");
//        verify(jwtToken).generateToken("testuser", "password", "ROLE_USER");
//    }
//
//    @Test
//    void testAuthenticateFailureInvalidCredentials() {
//        // Arrange: Prepare invalid credentials
//        AuthenticateUser authUser = new AuthenticateUser();
//        authUser.setUsername("invaliduser");
//        authUser.setPassword("invalidpassword");
//
//        // Mock AuthenticationManager to throw BadCredentialsException
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenThrow(new BadCredentialsException("Invalid username or password"));
//
//        // Act & Assert: Assert that BadCredentialsException is thrown
//        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
//            authController.authenticate(authUser);
//        });
//
//        // Assert: Verify the exception message
//        assertNotNull(exception);
//        assertEquals("Invalid username or password", exception.getMessage());
//
//        // Verify that authenticationManager.authenticate was called with the correct token
//        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
//    }
//
//
//    @Test
//    void testAuthenticateFailureInvalidRole() {
//        // Arrange
//        AuthenticateUser authUser = new AuthenticateUser();
//        authUser.setUsername("testuser");
//        authUser.setPassword("password");
//        authUser.setRole("ROLE_ADMIN");
//
//        User mockUser = new User();
//        mockUser.setUsername("testuser");
//        mockUser.setRoles(Collections.singletonList(new Role("ROLE_USER")));
//
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.isAuthenticated()).thenReturn(true);
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenReturn(authentication);
//        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
//
//        // Act
//        ResponseEntity<?> response = authController.authenticate(authUser);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        verify(userRepository).findByUsername("testuser");
//        verify(jwtToken, never()).generateToken(anyString(), anyString(), anyString());
//    }
//
//    
//   
//}
//
//
//    
//
//
//
