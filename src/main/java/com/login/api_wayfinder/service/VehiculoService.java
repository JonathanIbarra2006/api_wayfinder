package com.login.api_wayfinder.service;

import com.login.api_wayfinder.dto.VehiculoRequest;
import com.login.api_wayfinder.dto.VehiculoResponse;
import com.login.api_wayfinder.entity.Usuario;
import com.login.api_wayfinder.entity.Vehiculo;
import com.login.api_wayfinder.repository.UsuarioRepository;
import com.login.api_wayfinder.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Guardar un vehículo asignado al dueño
    public void guardarVehiculo(VehiculoRequest request, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setUsuario(usuario);
        vehiculo.setIdCategoria(request.getIdCategoria());
        vehiculo.setMarca(request.getMarca());
        vehiculo.setCilindrajeCc(request.getCilindrajeCc());
        vehiculo.setAutonomiaKm(request.getAutonomiaKm());

        vehiculoRepository.save(vehiculo);
    }

    // 2. Traer solo los vehículos de quien tiene la sesión iniciada
    public List<VehiculoResponse> obtenerMisVehiculos(String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return vehiculoRepository.findByUsuario_IdUsuario(usuario.getIdUsuario())
                .stream().map(vehiculo -> {
                    VehiculoResponse dto = new VehiculoResponse();
                    dto.setIdVehiculo(vehiculo.getIdVehiculo());
                    dto.setIdCategoria(vehiculo.getIdCategoria());
                    dto.setMarca(vehiculo.getMarca());
                    dto.setCilindrajeCc(vehiculo.getCilindrajeCc());
                    dto.setAutonomiaKm(vehiculo.getAutonomiaKm());
                    return dto;
                }).collect(Collectors.toList());
    }
}