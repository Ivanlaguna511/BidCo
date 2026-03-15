package com.bidco.api_rest.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import javax.crypto.SecretKey;

public class JwtUtil {

    // Asegúrate de que el secreto tenga una longitud adecuada para HS512 (por ejemplo, 64 bytes o más)
    private String secret = "TuSecretoMuySeguroQueDebeTenerUnTamañoAdecuadoParaHS512_64bytesMinimo!!!"; 
    private long expirationMillis = 86400000; // 1 día en milisegundos

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey()) // .setSigningKey ahora es .verifyWith
            .build()
            .parseSignedClaims(token)   // .parseClaimsJws ahora es .parseSignedClaims
            .getPayload();              // .getBody ahora es .getPayload
        }
    }
