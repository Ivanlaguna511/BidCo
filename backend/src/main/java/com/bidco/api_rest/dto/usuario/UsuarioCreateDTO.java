package com.bidco.api_rest.dto.usuario;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class UsuarioCreateDTO {

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 1, max = 100, message = "El nombre de usuario debe tener entre 1 y 100 caracteres")
    private String nombreUsuario;

    @NotNull(message = "El correo electrónico no puede ser nulo")
    @Email(message = "El correo electrónico debe ser válido")
    private String correoElectronico;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String contraseña;

    @NotNull(message = "El saldo no puede ser nulo")
    private BigDecimal saldo;

    @NotNull(message = "Los puntos no pueden ser nulos")
    private int puntos;

    @NotNull(message = "El país no puede ser nulo")
    private String pais;

    @NotNull(message = "La ciudad no puede ser nula")
    private String ciudad;

    @NotNull(message = "El código postal no puede ser nulo")
    private String codigoPostal;

    @NotNull(message = "La calle no puede ser nula")
    private String calle;

    @NotNull(message = "El número de piso no puede ser nulo")
    private int numeroPiso;
    
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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