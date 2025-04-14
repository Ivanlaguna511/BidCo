package com.bidco.api_rest.dto.subasta;

public class SubastaResponseDTO {

    private Long subastaID;
    private String nombreArticulo;
    private String precioFinal;


    public SubastaResponseDTO(Long subastaID, String nombreArticulo, String precioFinal) {
        this.subastaID = subastaID;
        this.nombreArticulo = nombreArticulo;
        this.precioFinal = precioFinal;
    }


    public Long getSubastaID() {
        return subastaID;
    }

    public void setSubastaID(Long subastaID) {
        this.subastaID = subastaID;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(String precioFinal) {
        this.precioFinal = precioFinal;
    }
}
