package com.bidco.api_rest.dto.admin;

public class ExpertCreateDTO {
    private String nombreUsuario;
    private String correoElectronico;
    private String contraseña;

    public ExpertCreateDTO() {}

    public ExpertCreateDTO(String nombreUsuario, String correoElectronico, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}