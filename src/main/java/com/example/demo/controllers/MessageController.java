package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Message;
import com.example.demo.repository.MessageRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "API de gestion des messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Operation(summary = "Créer un message", 
              description = "Envoie un nouveau message pour une location")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message envoyé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Map<String, Object> payload) {
        System.out.println("Received payload: " + payload);

        Message message = new Message();
        
        if (payload.get("rental_id") != null) {
            message.setRentalId(Long.valueOf(payload.get("rental_id").toString()));
        }
        if (payload.get("message") != null) {
            message.setMessage(payload.get("message").toString());
        }
        if (payload.get("user_id") != null) {
            message.setUserId(Long.valueOf(payload.get("user_id").toString()));
        }

        if (message.getRentalId() == null || message.getMessage() == null) {
            return ResponseEntity.badRequest().build();
        }

        message.setCreatedAt(new Date());
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    @Operation(summary = "Liste des messages d'une location", 
              description = "Récupère tous les messages associés à une location")
    @GetMapping("/rental/{rentalId}")
    public ResponseEntity<List<Message>> getMessagesByRental(
        @Parameter(description = "ID de la location") @PathVariable Long rentalId
    ) {
        List<Message> messages = messageRepository.findByRentalId(rentalId);
        return ResponseEntity.ok(messages);
    }
} 