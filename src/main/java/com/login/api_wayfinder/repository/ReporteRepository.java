package com.login.api_wayfinder.repository;

import com.login.api_wayfinder.entity.ReporteComunidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteComunidad, Integer> {
    // Retorna únicamente los reportes donde estado_activo sea true
    List<ReporteComunidad> findByEstadoActivoTrue();
}