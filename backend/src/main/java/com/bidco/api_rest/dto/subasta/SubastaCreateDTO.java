package com.bidco.api_rest.dto.subasta;

import com.bidco.api_rest.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SubastaCreateDTO {

    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private BigDecimal precioInicial;
    private boolean subastaNormal;
    private String nombreArticulo;
    private String descripcion;
    private Long creadorId;


    public SubastaCreateDTO(LocalDate fechaInicial, LocalDate fechaFinal, BigDecimal precioInicial,
                            boolean subastaNormal, String nombreArticulo, String descripcion,Long creadorId) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.precioInicial = precioInicial;
        this.subastaNormal = subastaNormal;
        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
        this.creadorId = creadorId;
    }


    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public BigDecimal getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(BigDecimal precioInicial) {
        this.precioInicial = precioInicial;
    }

    public boolean isSubastaNormal() {
        return subastaNormal;
    }

    public void setSubastaNormal(boolean subastaNormal) {
        this.subastaNormal = subastaNormal;
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

    public Long getCreadorId() {
        return creadorId;
    }

    public void setCreador(Long creadorId) {
        this.creadorId = creadorId;
    }
}
