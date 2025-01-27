package com.example.demo.dto;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserViewModel {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    public UserViewModel(Long id, String username, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.createdAt = createdAt != null ? sdf.format(createdAt) : null;
            this.updatedAt = updatedAt != null ? sdf.format(updatedAt) : null;
            System.out.println("Formatted dates - Created: " + this.createdAt + ", Updated: " + this.updatedAt);
        } catch (Exception e) {
            System.err.println("Error formatting dates: " + e.getMessage());
            this.createdAt = null;
            this.updatedAt = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
