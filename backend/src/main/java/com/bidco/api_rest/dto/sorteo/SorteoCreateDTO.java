package com.bidco.api_rest.dto.sorteo;

import com.bidco.api_rest.model.Trabajador;

import java.time.LocalDate;

import java.time.LocalDate;

public class SorteoCreateDTO {

    private String nombreArticulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int puntosNecesarios;
    private Long creadorId;


    // Constructor
    public SorteoCreateDTO(String nombreArticulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin,
                           int puntosNecesarios, Long creadorId) {
        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.puntosNecesarios = puntosNecesarios;
        this.creadorId = creadorId;
    }

    // Getters y Setters
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
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
