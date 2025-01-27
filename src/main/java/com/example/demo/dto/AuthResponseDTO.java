package com.example.demo.dto;
public class AuthResponseDTO {
    private String token;
    private String email;
    private String username;

    public AuthResponseDTO(String token, String email, String username) {
        this.token = token;
        this.email = email;
        this.username = username;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
} 