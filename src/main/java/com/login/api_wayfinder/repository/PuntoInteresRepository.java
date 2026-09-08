package com.login.api_wayfinder.repository;

import com.login.api_wayfinder.entity.PuntoInteres;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PuntoInteresRepository extends JpaRepository<PuntoInteres, Integer> {
    // Busca todos los puntos de interés que pertenezcan a una ruta específica
    List<PuntoInteres> findByIdRuta(Integer idRuta);
}