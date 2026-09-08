package com.login.api_wayfinder.controller;

import com.login.api_wayfinder.dto.LoginRequest;
import com.login.api_wayfinder.dto.RegistroRequest;
import com.login.api_wayfinder.entity.Usuario;
import com.login.api_wayfinder.repository.UsuarioRepository;
import com.login.api_wayfinder.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest request) {
        // 1. Buscamos al usuario por correo
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // 2. Comparamos la contraseña encriptada
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Contraseña incorrecta");
        }

        // 3. Si todo es correcto, fabricamos y entregamos el Token
        String token = jwtUtil.generarToken(usuario.getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroRequest request) {
        // 1. Verificamos si el correo ya existe
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El correo ya está registrado");
        }

        // 2. Creamos el nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombreCompleto());
        nuevoUsuario.setEmail(request.getEmail());

        // ¡Encriptamos la contraseña antes de guardarla!
        nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevoUsuario.setRol("USER");

        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("¡Usuario registrado exitosamente!");
    }
}