package com.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
 
import com.model.User;
import com.exception.Response;
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
 
//    @PutMapping("/manager/register/{user_id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long user_id) {
//        try {
//            // Fetch the existing user
//            User user = userService.getUserById(user_id);
//
//            // Check if the user already has the manager role before adding it
//            boolean hasManagerRole = user.getRoles().stream()
//                                                 .anyMatch(role -> role.getRole_name().equals("ROLE_MANAGER"));
//
//            if (!hasManagerRole) {
//                // Assign the manager role if not already assigned
//                Role employeeRole = new Role("ROLE_EMPLOYEE");
//                user.getRoles().add(employeeRole);
//                employeeRole.setUser(user);
//
//                // Save the new role and user
//                roleService.saveRole(employeeRole);
//                userService.saveUser(user);
//            }
//
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new Response("REGISTERSUCCESS", "User updated successfully with selected roles"));
//        } catch (Exception e) {
//            // Log the exception for better troubleshooting
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new Response("REGISTERFAIL", "Error creating user"));
//        }
//    }
}
 
 