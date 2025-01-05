package com.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
 
import com.model.User;
import com.exception.Response;
import com.model.Customer;
import com.model.Role;
import com.service.UserService;
import com.service.RoleService;
 
import java.util.ArrayList;
import java.util.List;
 
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private RoleService roleService;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
 
    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
 
        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role userRole = new Role();
        userRole.setRole_name("ROLE_USER"); // Default role for new users
        assignedRoles.add(userRole);
        userRole.setUser(user);
 
        user.setRoles(assignedRoles);
 
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("REGISTERSUCCESS", "User created successfully"));
        } catch (Exception e) {
            // Log the exception for better debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("REGISTERFAIL", "Error creating user"));
        }
    }
 
    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role adminRole = new Role();
        adminRole.setRole_name("ROLE_ADMIN"); // Role for Admin
        assignedRoles.add(adminRole);
        adminRole.setUser(user);
 
        user.setRoles(assignedRoles);
 
        try {
            // Save the user with the admin role
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("REGISTERSUCCESS", "Admin created successfully"));
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("REGISTERFAIL", "Error creating Admin"));
        }
    }
 
}
 
 