package com.login.api_wayfinder.controller;

import com.login.api_wayfinder.dto.RutaDTO;
import com.login.api_wayfinder.entity.Ruta;
import com.login.api_wayfinder.repository.RutaRepository;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    @Autowired
    private RutaRepository rutaRepository;

    @GetMapping
    public ResponseEntity<List<RutaDTO>> obtenerRutas() {
        List<Ruta> rutas = rutaRepository.findAll();

        List<RutaDTO> rutasFormateadas = rutas.stream().map(ruta -> {
            List<double[]> listaCoordenadas = new ArrayList<>();

            // Extraemos los puntos de la línea (LineString) a una lista de números
            for (Coordinate coord : ruta.getTrazado().getCoordinates()) {
                listaCoordenadas.add(new double[]{coord.getX(), coord.getY()});
            }

            return new RutaDTO(
                    ruta.getIdRuta(),
                    ruta.getNombre(),
                    ruta.getDescripcion(),
                    ruta.getDificultad(),
                    ruta.getDistanciaKm(),
                    listaCoordenadas
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(rutasFormateadas);
    }
}