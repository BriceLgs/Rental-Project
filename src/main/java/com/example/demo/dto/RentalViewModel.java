package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class RentalViewModel {
    
    private Long id;
    private String name;
    private Double surface;
    private BigDecimal price;
    private String picture;
    private String description;
    
    @JsonProperty("owner_id")
    private Long ownerId;
    
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private String createdAt;
    
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private String updatedAt;

    public RentalViewModel(Long id, String name, Double surface, Double price, 
                          String picture, String description, Long ownerId,
                          String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.price = BigDecimal.valueOf(price);
        this.picture = picture;
        this.description = description;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getSurface() { return surface; }
    public BigDecimal getPrice() { return price; }
    public String getPicture() { return picture; }
    public String getDescription() { return description; }
    public Long getOwnerId() { return ownerId; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSurface(Double surface) { this.surface = surface; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setPicture(String picture) { this.picture = picture; }
    public void setDescription(String description) { this.description = description; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
} 