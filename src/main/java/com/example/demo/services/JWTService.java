package com.example.demo.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;
import com.example.demo.entities.Users;

/**
 * Service gérant la génération et la validation des tokens JWT.
 */
@Service
public class JWTService {

    @Value("${jwt.key}")
    private String jwtKey;

    private Key key;
    public JWTService(@Value("${jwt.key}") String jwtKey) {
        if (jwtKey == null || jwtKey.isEmpty()) {
            throw new IllegalArgumentException("La clé secrète JWT est manquante dans le fichier de configuration");
        }
        this.key = Keys.hmacShaKeyFor(jwtKey.getBytes());
    }

    /**
     * Génère un token JWT pour un utilisateur authentifié.
     * 
     * @param authentication L'authentification de l'utilisateur
     * @return String Le token JWT généré
     */
    public String generateToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Users) {
            Users user = (Users) authentication.getPrincipal();
            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("userId", user.getId())
                    .claim("username", user.getDisplayUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }
        throw new IllegalArgumentException("Principal n'est pas une instance de Users");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
