package com.bidco.api_rest.dto.pujasorteo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PujaSorteoCreateDTO {

    private BigDecimal puntos;
    private LocalDate fecha;
    private Long sorteoId;
    private Long pujadorId;

    // Constructor
    public PujaSorteoCreateDTO(BigDecimal puntos, LocalDate fecha, Long sorteoId, Long pujadorId) {
        this.puntos = puntos;
        this.fecha = fecha;
        this.sorteoId = sorteoId;
        this.pujadorId = pujadorId;
    }

    public BigDecimal getPuntos() {
        return puntos;
    }

    public void setPuntos(BigDecimal puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getSorteoId() {
        return sorteoId;
    }

    public void setSorteoId(Long sorteoId) {
        this.sorteoId = sorteoId;
    }

    public Long getPujadorId() {
        return pujadorId;
    }

    public void setPujadorId(Long pujadorId) {
        this.pujadorId = pujadorId;
    }
}
