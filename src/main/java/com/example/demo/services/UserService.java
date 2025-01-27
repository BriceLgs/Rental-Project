package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserCreateModel;
import com.example.demo.dto.UserViewModel;
import com.example.demo.entities.Users;
import com.example.demo.repository.UserRepository;

/**
 * Service gérant les opérations liées aux utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Crée un nouvel utilisateur dans le système.
     * 
     * @param userCreateModel Les données de l'utilisateur à créer
     * @return UserViewModel Les informations de l'utilisateur créé
     * @throws IllegalArgumentException Si l'email est déjà utilisé
     */
    public UserViewModel saveUser(UserCreateModel userCreateModel) {
        if (userRepository.existsByEmail(userCreateModel.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        if (userCreateModel.getPassword() == null || userCreateModel.getPassword().length() < 8) {
            throw new IllegalArgumentException("Mot de passe trop court");
        }

        if (userCreateModel.getUsername() == null || userCreateModel.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Nom d'utilisateur requis");
        }

        System.out.println("Sauvegarde de l'utilisateur : " + userCreateModel);
        String encodedPassword = passwordEncoder.encode(userCreateModel.getPassword());

        Users newUser = new Users();
        newUser.setUsername(userCreateModel.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setEmail(userCreateModel.getEmail());
        
        // Les dates seront gérées par @PrePersist
        Users savedUser = userRepository.save(newUser);

        return new UserViewModel(
            savedUser.getId(), 
            savedUser.getDisplayUsername(), 
            savedUser.getEmail(), 
            savedUser.getCreatedAt(), 
            savedUser.getUpdatedAt()
        );
    }

    /**
     * Recherche un utilisateur par son email.
     * 
     * @param email L'email de l'utilisateur à rechercher
     * @return Optional<Users> L'utilisateur trouvé ou un Optional vide
     */
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }
}
