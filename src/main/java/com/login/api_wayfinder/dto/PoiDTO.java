package com.login.api_wayfinder.dto;

public class PoiDTO {
    private Integer idPoi;
    private String nombre;
    private Integer idTipo;
    private double latitud;
    private double longitud;

    public PoiDTO(Integer idPoi, String nombre, Integer idTipo, double latitud, double longitud) {
        this.idPoi = idPoi;
        this.nombre = nombre;
        this.idTipo = idTipo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // --- GETTERS ---
    public Integer getIdPoi() { return idPoi; }
    public String getNombre() { return nombre; }
    public Integer getIdTipo() { return idTipo; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
}