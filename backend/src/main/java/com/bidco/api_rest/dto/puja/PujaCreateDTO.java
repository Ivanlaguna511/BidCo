package com.bidco.api_rest.dto.puja;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PujaCreateDTO {

    private BigDecimal importe;
    private LocalDate fecha;
    private Long subastaId;
    private Long pujadorId;

    // Constructor
    public PujaCreateDTO(BigDecimal importe, LocalDate fecha, Long subastaId, Long pujadorId) {
        this.importe = importe;
        this.fecha = fecha;
        this.subastaId = subastaId;
        this.pujadorId = pujadorId;
    }

    // Getters y Setters
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

    public Long getSubastaID() {
        return subastaId;
    }

    public void setSubastaID(Long subastaId) {
        this.subastaId = subastaId;
    }

    public Long getPujadorId() {
        return pujadorId;
    }

    public void setPujadorId(Long pujadorId) {
        this.pujadorId = pujadorId;
    }
}

