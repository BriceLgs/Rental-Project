package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import com.example.demo.repository.RentalPictureRepository;
import com.example.demo.entities.Rental;
import com.example.demo.entities.RentalPicture;
import com.example.demo.repository.RentalRepository;
import com.example.demo.entities.Users;
import lombok.extern.slf4j.Slf4j;

/**
 * Service gérant les opérations liées aux locations.
 */
@Slf4j
@Service
public class RentalService {

    private final String uploadPath = "src/main/resources/static/uploads";

    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private RentalPictureRepository rentalPictureRepository;

    @Autowired
    private UserService userService;

    /**
     * Récupère toutes les locations.
     * 
     * @return List<Rental> La liste de toutes les locations
     */
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRental(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental createRentalWithOwner(
        String name, 
        Double surface, 
        Double price, 
        String description, 
        MultipartFile image,
        Authentication authentication
    ) {
        String username = authentication.getName();
        Users user = userService.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setOwnerId(user.getId());
        rental.setCreatedAt(new Date());
        rental.setUpdatedAt(new Date());
        
        return createRental(rental, image);
    }

    /**
     * Crée une nouvelle location avec une image.
     * 
     * @param rental L'objet Rental à créer
     * @param image L'image à uploader
     * @return Rental La location créée
     * @throws RuntimeException Si l'upload de l'image échoue
     */
    public Rental createRental(Rental rental, MultipartFile image) {
        try {
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
                log.info("Création du répertoire d'upload : {}", uploadDir);
            }
            
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);
            
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.debug("Image sauvegardée : {}", fileName);

            String imageUrl = "http://localhost:3001/uploads/" + fileName;
            Rental savedRental = rentalRepository.save(rental);
            log.info("Location créée avec succès : {}", savedRental.getId());

            RentalPicture rentalPicture = new RentalPicture();
            rentalPicture.setRentalId(savedRental.getId());
            rentalPicture.setPicture(imageUrl);
            rentalPictureRepository.save(rentalPicture);

            return savedRental;
        } catch (IOException e) {
            log.error("Erreur lors de l'upload de l'image", e);
            throw new RuntimeException("Erreur lors de l'upload de l'image", e);
        }
    }
}