package com.bidco.api_rest.dto.trabajador;

public class TrabajadorResponseDTO {

    private Long trabajadorID;
    private String nombreUsuario;
    private String correoElectronico;

    // Constructor
    public TrabajadorResponseDTO(Long trabajadorID, String nombreUsuario, String correoElectronico) {
        this.trabajadorID = trabajadorID;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public Long getTrabajadorID() {
        return trabajadorID;
    }

    public void setTrabajadorID(Long trabajadorID) {
        this.trabajadorID = trabajadorID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
