package com.login.api_wayfinder.controller;

import com.login.api_wayfinder.dto.VehiculoRequest;
import com.login.api_wayfinder.dto.VehiculoResponse;
import com.login.api_wayfinder.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // POST: /api/vehiculos
    @PostMapping
    public ResponseEntity<String> registrarVehiculo(
            @RequestBody VehiculoRequest request,
            Authentication authentication) {
        try {
            String emailUsuario = authentication.getName(); // El Token nos dice quién es
            vehiculoService.guardarVehiculo(request, emailUsuario);
            return ResponseEntity.status(201).body("Vehículo registrado en tu garaje");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar: " + e.getMessage());
        }
    }

    // GET: /api/vehiculos
    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> obtenerMisVehiculos(Authentication authentication) {
        String emailUsuario = authentication.getName();
        return ResponseEntity.ok(vehiculoService.obtenerMisVehiculos(emailUsuario));
    }
}
