package com.bidco.api_rest.dto.admin;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

public class SorteoCreateDTO {
    private String nombreArticulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer puntosNecesarios;
    private MultipartFile imagen;

    public SorteoCreateDTO() {}

    public SorteoCreateDTO(String nombreArticulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Integer puntosNecesarios, MultipartFile imagen) {
        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.puntosNecesarios = puntosNecesarios;
        this.imagen = imagen;
    }

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

    public Integer getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(Integer puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }
}