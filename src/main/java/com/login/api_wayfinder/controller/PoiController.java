package com.login.api_wayfinder.controller;

import com.login.api_wayfinder.dto.PoiDTO;
import com.login.api_wayfinder.entity.PuntoInteres;
import com.login.api_wayfinder.repository.PuntoInteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pois")
public class PoiController {

    @Autowired
    private PuntoInteresRepository poiRepository;

    @GetMapping("/ruta/{idRuta}")
    public ResponseEntity<List<PoiDTO>> obtenerPoisPorRuta(@PathVariable Integer idRuta) {
        List<PuntoInteres> puntos = poiRepository.findByIdRuta(idRuta);

        List<PoiDTO> poisFormateados = puntos.stream().map(poi -> {
            return new PoiDTO(
                    poi.getIdPoi(),
                    poi.getNombre(),
                    poi.getIdTipo(),
                    poi.getCoordenadas().getY(), // Y = Latitud
                    poi.getCoordenadas().getX()  // X = Longitud
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(poisFormateados);
    }

    // 👇 MÉTODO CORREGIDO
    @GetMapping("/categoria/{nombreCategoria}")
    public ResponseEntity<?> obtenerPoisPorCategoria(@PathVariable String nombreCategoria) {
        int idTipo = 0;

        // Traducimos el texto que envía Flutter al ID de la base de datos
        switch (nombreCategoria) {
            case "Gasolineras": idTipo = 1; break;
            case "Restaurantes": idTipo = 2; break;
            case "Miradores": idTipo = 3; break;
            case "Talleres": idTipo = 4; break;
            default: return ResponseEntity.badRequest().body("Categoría no válida");
        }

        // CORRECCIÓN 1: Usamos la variable correcta "poiRepository"
        List<PuntoInteres> puntos = poiRepository.findByIdTipo(idTipo);

        // CORRECCIÓN 2: Mapeamos la entidad al PoiDTO para que Flutter reciba latitud y longitud limpios
        List<PoiDTO> poisFormateados = puntos.stream().map(poi -> {
            return new PoiDTO(
                    poi.getIdPoi(),
                    poi.getNombre(),
                    poi.getIdTipo(),
                    poi.getCoordenadas().getY(), // Y = Latitud
                    poi.getCoordenadas().getX()  // X = Longitud
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(poisFormateados);
    }
}