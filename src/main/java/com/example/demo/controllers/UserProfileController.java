package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.UserService;
import com.example.demo.dto.UserViewModel;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@Tag(name = "User Profile", description = "API de gestion des profils utilisateurs")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserViewModel> getUserById(@PathVariable Long id) {
        return userService.findById(id)
            .map(user -> new UserViewModel(
                user.getId(),
                user.getDisplayUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
            ))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
} 