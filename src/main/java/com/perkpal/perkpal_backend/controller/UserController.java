package com.perkpal.perkpal_backend.controller;

import com.perkpal.perkpal_backend.dto.UserRegistrationDTO;
import com.perkpal.perkpal_backend.model.User;
import com.perkpal.perkpal_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5501") // Match frontend port
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO dto) {
        try {
            User savedUser = userService.registerUser(dto);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        Optional<User> loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        return loggedInUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).body(null));
    }
}