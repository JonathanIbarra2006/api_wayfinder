package com.login.api_wayfinder.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_comunidad")
public class ReporteComunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportes_seq_gen")
    @SequenceGenerator(name = "reportes_seq_gen", sequenceName = "reportes_seq", allocationSize = 1)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "tipo_alerta")
    private String tipoAlerta;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point coordenadas;

    @Column(name = "estado_activo")
    private Boolean estadoActivo = true;

    @Column(name = "fecha_reporte", insertable = false, updatable = false)
    private LocalDateTime fechaReporte;

    // NUEVO CAMPO: Contador de votos negativos
    @Column(name = "votos_negativos")
    private Integer votosNegativos = 0;

    // --- GETTERS Y SETTERS COMPLETO ---
    public Integer getIdReporte() { return idReporte; }
    public void setIdReporte(Integer idReporte) { this.idReporte = idReporte; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }

    public Point getCoordenadas() { return coordenadas; }
    public void setCoordenadas(Point coordenadas) { this.coordenadas = coordenadas; }

    public Boolean getEstadoActivo() { return estadoActivo; }
    public void setEstadoActivo(Boolean estadoActivo) { this.estadoActivo = estadoActivo; }

    public LocalDateTime getFechaReporte() { return fechaReporte; }

    // NUEVOS GETTERS Y SETTERS
    public Integer getVotosNegativos() { return votosNegativos; }
    public void setVotosNegativos(Integer votosNegativos) { this.votosNegativos = votosNegativos; }
}