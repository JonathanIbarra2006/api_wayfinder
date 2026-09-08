package com.login.api_wayfinder.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Genera una clave secreta súper segura automáticamente (Requisito de JJWT 0.11+)
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Método para crear el token cuando el usuario inicie sesión
    public String generarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // El token expira en 10 horas
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extrae el correo (Subject) escondido dentro del Token
    public String extraerEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Verifica si el token pertenece a ese correo y si aún es válido
    public boolean validarToken(String token, String email) {
        return (extraerEmail(token).equals(email) && !isTokenExpired(token));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}