package com.login.api_wayfinder.controller;

import com.login.api_wayfinder.dto.ReporteRequest;
import com.login.api_wayfinder.dto.ReporteResponse;
import com.login.api_wayfinder.entity.ReporteComunidad;
import com.login.api_wayfinder.repository.ReporteRepository;
import com.login.api_wayfinder.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // NUEVO: Inyectamos el repositorio para poder buscar y modificar reportes específicos
    @Autowired
    private ReporteRepository reporteRepository;

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

    // NUEVO ENDPOINT: Recibe los votos de confirmación o descarte
    @PostMapping("/{id}/votar")
    public ResponseEntity<String> votarReporte(@PathVariable Integer id, @RequestParam String accion) {
        Optional<ReporteComunidad> reporteOpt = reporteRepository.findById(id);

        if (reporteOpt.isPresent()) {
            ReporteComunidad reporte = reporteOpt.get();

            if ("descartar".equalsIgnoreCase(accion)) {
                int votosActuales = reporte.getVotosNegativos() != null ? reporte.getVotosNegativos() : 0;
                reporte.setVotosNegativos(votosActuales + 1);

                // Imprime el conteo exacto en tu terminal de IntelliJ
                System.out.println("⚠️ REPORTE " + id + " | Voto negativo recibido. Total actuales: " + reporte.getVotosNegativos() + "/3");

                if (reporte.getVotosNegativos() >= 3) {
                    reporteRepository.delete(reporte);
                    System.out.println("🗑️ REPORTE " + id + " | Eliminado definitivamente del mapa.");
                    return ResponseEntity.ok("Alerta eliminada del mapa por la comunidad.");
                }
            }

            reporteRepository.save(reporte);
            return ResponseEntity.ok("Voto registrado con éxito.");
        }
        return ResponseEntity.notFound().build();
    }
}