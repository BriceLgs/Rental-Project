package com.example.demo.controllers;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.JWTService;
import com.example.demo.services.UserService;
import com.example.demo.dto.UserCreateModel;
import com.example.demo.dto.UserViewModel;
import com.example.demo.entities.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo.dto.AuthResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API d'authentification")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;
    
    public UserController(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Inscription d'un utilisateur", 
              description = "Permet de créer un nouveau compte utilisateur")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscription réussie"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateModel user) {
        try {
            UserViewModel createdUser = userService.saveUser(user);
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            String token = jwtService.generateToken(authentication);
            AuthResponseDTO response = new AuthResponseDTO(
                token,
                createdUser.getEmail(),
                createdUser.getUsername()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Connexion utilisateur", 
              description = "Authentifie un utilisateur et retourne un token JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserCreateModel loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            String token = jwtService.generateToken(authentication);
            Users user = (Users) authentication.getPrincipal();
            
            AuthResponseDTO response = new AuthResponseDTO(
                token,
                user.getEmail(),
                user.getDisplayUsername()
            );
            
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @Operation(summary = "Obtenir les informations de l'utilisateur connecté")
    @GetMapping("/me")
    public ResponseEntity<UserViewModel> getCurrentUser(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            Optional<Users> userOpt = userService.findByEmail(email);
            
            if (userOpt.isPresent()) {
                Users currentUser = userOpt.get();
                UserViewModel userView = new UserViewModel(
                    currentUser.getId(),
                    currentUser.getDisplayUsername(),
                    currentUser.getEmail(),
                    currentUser.getCreatedAt(),
                    currentUser.getUpdatedAt()
                );
                
                log.debug("Utilisateur trouvé : {}", currentUser.getEmail());
                return ResponseEntity.ok(userView);
            }
        }
        log.warn("Tentative d'accès non autorisée à /me");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
