package com.bidco.api_rest.dto.pujasorteo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PujaSorteoResponseDTO {

    private Long pujaID;
    private BigDecimal puntos;
    private LocalDate fecha;
    private Long sorteoID;
    private Long pujadorID;

    // Constructor
    public PujaSorteoResponseDTO(Long pujaID,Long pujaSorteoID, BigDecimal puntos, LocalDate fecha, Long sorteoID, Long pujadorID) {
        this.pujaID = pujaID;

        this.puntos = puntos;
        this.fecha = fecha;
        this.sorteoID = sorteoID;
        this.pujadorID = pujadorID;
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

    public Long getSorteoID() {
        return sorteoID;
    }

    public void setSorteoID(Long sorteoID) {
        this.sorteoID = sorteoID;
    }

    public Long getPujadorID() {
        return pujadorID;
    }

    public void setPujadorID(Long pujadorID) {
        this.pujadorID = pujadorID;
    }

    public Long getPujaID() {
        return pujaID;
    }

    public void setPujaID(Long pujaID) {
        this.pujaID = pujaID;
    }
}
