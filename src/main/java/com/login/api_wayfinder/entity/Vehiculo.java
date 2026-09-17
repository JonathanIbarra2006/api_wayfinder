package com.login.api_wayfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos_usuario")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehiculos_seq_gen")
    @SequenceGenerator(name = "vehiculos_seq_gen", sequenceName = "vehiculos_seq", allocationSize = 1)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    // Vinculamos el vehículo a su dueño
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "marca")
    private String marca;

    @Column(name = "cilindraje_cc")
    private Integer cilindrajeCc;

    @Column(name = "autonomia_km")
    private Integer autonomiaKm;

    // --- GETTERS Y SETTERS ---

    public Integer getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Integer idVehiculo) { this.idVehiculo = idVehiculo; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Integer getCilindrajeCc() { return cilindrajeCc; }
    public void setCilindrajeCc(Integer cilindrajeCc) { this.cilindrajeCc = cilindrajeCc; }

    public Integer getAutonomiaKm() { return autonomiaKm; }
    public void setAutonomiaKm(Integer autonomiaKm) { this.autonomiaKm = autonomiaKm; }
}