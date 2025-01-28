package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageViewModel {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("rental_id")
    private Long rentalId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("created_at")
    private String createdAt;

    public MessageViewModel(Long id, Long rentalId, Long userId, String message, String createdAt) {
        this.id = id;
        this.rentalId = rentalId;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRentalId() { return rentalId; }
    public void setRentalId(Long rentalId) { this.rentalId = rentalId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
} 