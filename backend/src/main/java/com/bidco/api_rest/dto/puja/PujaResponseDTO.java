package com.bidco.api_rest.dto.puja;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PujaResponseDTO {

    private Long pujaID;
    private BigDecimal importe;
    private LocalDate fecha;
    private boolean ganadora;
    private Long subastaID;
    private Long pujadorID;

    // Constructor
    public PujaResponseDTO(Long pujaID, BigDecimal importe, LocalDate fecha, boolean ganadora, Long subastaID, Long pujadorID) {
        this.pujaID = pujaID;
        this.importe = importe;
        this.fecha = fecha;
        this.ganadora = ganadora;
        this.subastaID = subastaID;
        this.pujadorID = pujadorID;
    }

    // Getters y Setters
    public Long getPujaID() {
        return pujaID;
    }

    public void setPujaID(Long pujaID) {
        this.pujaID = pujaID;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isGanadora() {
        return ganadora;
    }

    public void setGanadora(boolean ganadora) {
        this.ganadora = ganadora;
    }

    public Long getSubastaID() {
        return subastaID;
    }

    public void setSubastaID(Long subastaID) {
        this.subastaID = subastaID;
    }

    public Long getPujadorID() {
        return pujadorID;
    }

    public void setPujadorID(Long pujadorID) {
        this.pujadorID = pujadorID;
    }


}
