package com.bidco.api_rest.dto.sorteo;

import java.time.LocalDate;

public class SorteoResponseDTO {

    private Long sorteoID;
    private String nombreArticulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int puntosNecesarios;
    private int puntosFinales;
    private Long creadorId;
    private String imagen;


    // Constructor
    public SorteoResponseDTO(Long sorteoID, String nombreArticulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin,
                           int puntosNecesarios, int puntosFinales, Long creadorId, String imagen) {
        this.sorteoID = sorteoID;
        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.puntosNecesarios = puntosNecesarios;
        this.puntosFinales = puntosFinales;
        this.creadorId = creadorId;
        this.imagen = imagen;
    }

    // Getters y Setters
    public Long getSorteoID() {
        return sorteoID;
    }

    public void setSorteoID(Long sorteoID) {
        this.sorteoID = sorteoID;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getPuntosFinales() {
        return puntosFinales;
    }

    public void setPuntosFinales(int puntosFinales) {
        this.puntosFinales = puntosFinales;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(int puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }

    public Long getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Long creadorId) {
        this.creadorId = creadorId;
    }
}
