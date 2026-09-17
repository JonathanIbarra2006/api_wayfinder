package com.login.api_wayfinder.service;

import com.login.api_wayfinder.dto.ReporteRequest;
import com.login.api_wayfinder.dto.ReporteResponse;
import com.login.api_wayfinder.entity.ReporteComunidad;
import com.login.api_wayfinder.entity.Usuario;
import com.login.api_wayfinder.repository.ReporteRepository;
import com.login.api_wayfinder.repository.UsuarioRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    // Método 1: Guardar un reporte nuevo (POST)
    public void guardarReporte(ReporteRequest request, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Coordinate coordenada = new Coordinate(request.getLongitud(), request.getLatitud());
        Point puntoEspacial = geometryFactory.createPoint(coordenada);

        ReporteComunidad reporte = new ReporteComunidad();
        reporte.setUsuario(usuario);
        reporte.setTipoAlerta(request.getTipoAlerta());
        reporte.setCoordenadas(puntoEspacial);

        reporteRepository.save(reporte);
    }

    // Método 2: Leer todos los reportes vigentes (GET)
    public List<ReporteResponse> obtenerReportesActivos() {
        return reporteRepository.findByEstadoActivoTrue().stream().map(reporte -> {
            ReporteResponse dto = new ReporteResponse();
            dto.setIdReporte(reporte.getIdReporte());
            dto.setTipoAlerta(reporte.getTipoAlerta());
            // Extraemos Latitud (Y) y Longitud (X)
            dto.setLatitud(reporte.getCoordenadas().getY());
            dto.setLongitud(reporte.getCoordenadas().getX());
            return dto;
        }).collect(Collectors.toList());
    }
}