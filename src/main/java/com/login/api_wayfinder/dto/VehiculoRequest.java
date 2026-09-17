package com.login.api_wayfinder.dto;

public class VehiculoRequest {
    private Integer idCategoria;
    private String marca;
    private Integer cilindrajeCc;
    private Integer autonomiaKm;

    // --- GETTERS Y SETTERS ---
    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public Integer getCilindrajeCc() { return cilindrajeCc; }
    public void setCilindrajeCc(Integer cilindrajeCc) { this.cilindrajeCc = cilindrajeCc; }
    public Integer getAutonomiaKm() { return autonomiaKm; }
    public void setAutonomiaKm(Integer autonomiaKm) { this.autonomiaKm = autonomiaKm; }
}
