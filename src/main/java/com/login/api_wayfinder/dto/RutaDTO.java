package com.login.api_wayfinder.dto;

import java.util.List;

public class RutaDTO {
    private Integer idRuta;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private Double distanciaKm;
    private List<double[]> coordenadas; // Lista de pares [longitud, latitud]

    public RutaDTO(Integer idRuta, String nombre, String descripcion, String dificultad, Double distanciaKm, List<double[]> coordenadas) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.distanciaKm = distanciaKm;
        this.coordenadas = coordenadas;
    }

    // --- GETTERS ---
    public Integer getIdRuta() { return idRuta; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getDificultad() { return dificultad; }
    public Double getDistanciaKm() { return distanciaKm; }
    public List<double[]> getCoordenadas() { return coordenadas; }
}