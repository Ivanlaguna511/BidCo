package com.bidco.api_rest.dto.cometario;

import java.math.BigDecimal;

public class ComentarioCreateDTO {

    private String comentario;
    private BigDecimal precioEstimado;
    private Long trabajadorId; // Ahora enviamos el nombre de usuario
    private Long subastaID;
    // Constructor
    public ComentarioCreateDTO(String comentario, BigDecimal precioEstimado, Long trabajadorId, Long subastaID) {
        this.comentario = comentario;
        this.precioEstimado = precioEstimado;
        this.trabajadorId = trabajadorId;
        this.subastaID = subastaID;
    }

    // Getters y Setters
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Long trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public Long getSubastaID() {
        return subastaID;
    }

    public void setSubastaID(Long subastaID) {
        this.subastaID = subastaID;
    }

    public BigDecimal getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(BigDecimal precioEstimado) {
        this.precioEstimado = precioEstimado;
    }
}