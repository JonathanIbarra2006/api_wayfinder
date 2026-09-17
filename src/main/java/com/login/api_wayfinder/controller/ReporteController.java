package com.login.api_wayfinder.controller;

import com.login.api_wayfinder.dto.ReporteRequest;
import com.login.api_wayfinder.dto.ReporteResponse;
import com.login.api_wayfinder.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Endpoint POST: Crear un nuevo reporte
    @PostMapping
    public ResponseEntity<String> crearReporte(
            @RequestBody ReporteRequest request,
            Authentication authentication) {

        try {
            String emailUsuario = authentication.getName();
            reporteService.guardarReporte(request, emailUsuario);
            return ResponseEntity.ok("Reporte creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el reporte: " + e.getMessage());
        }
    }

    // Endpoint GET: Obtener todos los reportes
    @GetMapping
    public ResponseEntity<List<ReporteResponse>> obtenerReportesActivos() {
        return ResponseEntity.ok(reporteService.obtenerReportesActivos());
    }
}