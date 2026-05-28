package com.bidco.api_rest.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final long EXPIRATION_MILLIS = 86400000; // 24 horas

    private final String secret;

    public JwtUtil() {
        String envSecret = System.getenv("JWT_SECRET");
        // Si la variable de entorno no está definida, usamos un fallback de desarrollo
        // En Render siempre deberías tener JWT_SECRET configurado
        this.secret = (envSecret != null && !envSecret.isBlank())
                ? envSecret
                : "bidco-dev-secret-key-must-be-at-least-32-chars-long";
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }
}