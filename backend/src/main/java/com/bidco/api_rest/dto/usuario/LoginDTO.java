// LoginDTO.java
package com.bidco.api_rest.dto.usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @NotNull(message = "El identificador (correo o nombre de usuario) es obligatorio")
    @Size(min = 1, max = 100, message = "El identificador debe tener entre 1 y 100 caracteres")
    private String identificador; // Puede ser correo o nombre de usuario

    @NotNull(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String contraseña;

    // Getters y setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}