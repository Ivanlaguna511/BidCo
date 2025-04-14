package com.bidco.api_rest.dto.usuario;

public class UsuarioResponseDTO {

    private Long usuarioID;
    private String nombreUsuario;
    private String correoElectronico;

    // Constructor
    public UsuarioResponseDTO(Long usuarioID, String nombreUsuario, String correoElectronico) {
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
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