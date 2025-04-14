package com.bidco.api_rest.dto.sorteo;



public class SorteoResponseDTO {

    private Long sorteoID;
    private String nombreArticulo;
    private int puntosFinales;


    // Constructor
    public SorteoResponseDTO(Long sorteoID, String nombreArticulo,int puntosFinales) {
        this.sorteoID = sorteoID;
        this.nombreArticulo = nombreArticulo;
        this.puntosFinales = puntosFinales;
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
}
