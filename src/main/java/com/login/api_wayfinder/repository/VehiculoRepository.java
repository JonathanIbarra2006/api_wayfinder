package com.login.api_wayfinder.repository;

import com.login.api_wayfinder.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    // Este método automático buscará todos los vehículos que pertenezcan al ID de un usuario específico
    List<Vehiculo> findByUsuario_IdUsuario(Integer idUsuario);
}