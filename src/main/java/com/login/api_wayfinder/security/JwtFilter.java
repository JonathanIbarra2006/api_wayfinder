package com.login.api_wayfinder.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. Buscamos el token en la cabecera "Authorization"
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        // 2. Verificamos que traiga la palabra "Bearer " (Portador)
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Quitamos "Bearer " para dejar solo el token
            try {
                email = jwtUtil.extraerEmail(jwt);
            } catch (Exception e) {
                System.out.println("Filtro JWT: Token inválido o expirado");
            }
        }

        // 3. Si encontramos un correo y el usuario aún no ha sido autorizado en esta petición
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Verificamos matemáticamente el token
            if (jwtUtil.validarToken(jwt, email)) {
                // Le damos acceso total al sistema
                UsernamePasswordAuthenticationToken accesoVIP = new UsernamePasswordAuthenticationToken(
                        email, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(accesoVIP);
            }
        }

        // 4. Continuamos con la petición
        chain.doFilter(request, response);
    }
}