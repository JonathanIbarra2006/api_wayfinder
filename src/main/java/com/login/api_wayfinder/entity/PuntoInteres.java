package com.login.api_wayfinder.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "puntos_interes")
public class PuntoInteres {

    @Id
    @Column(name = "id_poi")
    private Integer idPoi;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "id_ruta")
    private Integer idRuta;

    // Point es el tipo de dato para puntos exactos (latitud y longitud)
    @Column(name = "coordenadas", columnDefinition = "geometry(Point,4326)")
    private Point coordenadas;

    // --- GETTERS Y SETTERS ---
    public Integer getIdPoi() { return idPoi; }
    public void setIdPoi(Integer idPoi) { this.idPoi = idPoi; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getIdTipo() { return idTipo; }
    public void setIdTipo(Integer idTipo) { this.idTipo = idTipo; }

    public Integer getIdRuta() { return idRuta; }
    public void setIdRuta(Integer idRuta) { this.idRuta = idRuta; }

    public Point getCoordenadas() { return coordenadas; }
    public void setCoordenadas(Point coordenadas) { this.coordenadas = coordenadas; }
}