package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.RentalService;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;

import com.example.demo.entities.Rental;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo.dto.RentalsResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/rentals")
@Tag(name = "Rentals", description = "API de gestion des locations")
public class RentalController {

	@Autowired
	private RentalService rentalService;
	@Operation(summary = "Liste des locations", 
			  description = "Récupère la liste de toutes les locations disponibles")
	@GetMapping
	public ResponseEntity<RentalsResponseDTO> getAllRentals() {
		List<Rental> rentals = rentalService.getAllRentals();
		return ResponseEntity.ok(new RentalsResponseDTO(rentals));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rental> getRental(@PathVariable Long id) {
		return rentalService.getRental(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Créer une location", 
			  description = "Crée une nouvelle location avec une image")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Location créée avec succès"),
		@ApiResponse(responseCode = "400", description = "Données invalides")
	})
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Rental> createRental(
		@Parameter(description = "Nom de la location") @RequestParam("name") String name,
		@Parameter(description = "Surface en m²") @RequestParam("surface") Double surface,
		@Parameter(description = "Prix") @RequestParam("price") Double price,
		@Parameter(description = "Description") @RequestParam("description") String description,
		@Parameter(description = "Image de la location") @RequestParam("picture") MultipartFile image,
		Authentication authentication
	) {
		try {
			if (image == null || image.isEmpty()) {
				log.warn("Tentative de création d'une location sans image");
				return ResponseEntity.badRequest().body(null);
			}

			return ResponseEntity.ok(rentalService.createRentalWithOwner(
				name, surface, price, description, image, authentication
			));
		} catch (Exception e) {
			log.error("Erreur lors de la création de la location", e);
			return ResponseEntity.badRequest().build();
		}
	}
}
