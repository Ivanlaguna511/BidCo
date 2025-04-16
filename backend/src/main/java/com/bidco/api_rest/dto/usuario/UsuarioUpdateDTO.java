package com.bidco.api_rest.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateDTO {

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 1, max = 100, message = "El nombre de usuario debe tener entre 1 y 100 caracteres")
    private String nombreUsuario;

    @NotNull(message = "El correo electrónico no puede ser nulo")
    @Email(message = "El correo electrónico debe ser válido")
    private String correoElectronico;

    @NotNull(message = "La ciudad no puede ser nula")
    @Size(min = 1, max = 100, message = "La ciudad debe tener entre 1 y 100 caracteres")
    private String ciudad;

    @NotNull(message = "El código postal no puede ser nulo")
    @Size(min = 1, max = 20, message = "El código postal debe tener entre 1 y 20 caracteres")
    private String codigoPostal;

    @NotNull(message = "La calle no puede ser nula")
    @Size(min = 1, max = 255, message = "La calle debe tener entre 1 y 255 caracteres")
    private String calle;

    @NotNull(message = "El número de piso no puede ser nulo")
    private int numeroPiso;

    // LetraPiso es opcional
    private String letraPiso;

    // Getters y setters

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
    
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public int getNumeroPiso() {
        return numeroPiso;
    }
    public void setNumeroPiso(int numeroPiso) {
        this.numeroPiso = numeroPiso;
    }
    
    public String getLetraPiso() {
        return letraPiso;
    }
    public void setLetraPiso(String letraPiso) {
        this.letraPiso = letraPiso;
    }
}