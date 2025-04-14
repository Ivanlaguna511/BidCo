package com.bidco.api_rest.dto.trabajador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TrabajadorCreateDTO {

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres")
    private String nombreUsuario;

    @NotNull(message = "El correo electrónico no puede ser nulo")
    @Email(message = "El correo electrónico debe ser válido")
    private String correoElectronico;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String contraseña;

    private boolean experto;

    // Getters y Setters

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

    public boolean isExperto() {
        return experto;
    }

    public void setExperto(boolean experto) {
        this.experto = experto;
    }
}
