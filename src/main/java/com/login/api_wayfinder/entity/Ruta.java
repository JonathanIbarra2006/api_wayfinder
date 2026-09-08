package com.login.api_wayfinder.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.LineString;

@Entity
@Table(name = "rutas")
public class Ruta {

    @Id
    @Column(name = "id_ruta")
    private Integer idRuta;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "dificultad")
    private String dificultad;

    @Column(name = "distancia_km")
    private Double distanciaKm;

    // LineString es el tipo de dato para trazar carreteras
    @Column(name = "trazado", columnDefinition = "geometry(LineString,4326)")
    private LineString trazado;

    // --- GETTERS Y SETTERS ---
    public Integer getIdRuta() { return idRuta; }
    public void setIdRuta(Integer idRuta) { this.idRuta = idRuta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }

    public Double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(Double distanciaKm) { this.distanciaKm = distanciaKm; }

    public LineString getTrazado() { return trazado; }
    public void setTrazado(LineString trazado) { this.trazado = trazado; }
}
