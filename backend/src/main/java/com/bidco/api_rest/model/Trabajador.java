package com.bidco.api_rest.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trabajador_id")
    private Long trabajadorID;

    @Column(name = "nombre_usuario",nullable = false,unique = true)
    private String nombreUsuario;

    @Column(name = "correo_electronico",nullable = false,unique = true)
    private String correoElectronico;

    @Column(nullable = false)
    private String contraseña;

    @Column(name = "rol_trabajador",nullable = false)
    private boolean experto;

    @OneToMany(mappedBy = "trabajador",fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "creador",fetch = FetchType.LAZY)
    private List<Sorteo> sorteos;

    public Trabajador(Long trabajadorID, String nombreUsuario, String correoElectronico, String contraseña,boolean experto) {
        this.trabajadorID = trabajadorID;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.experto=experto;
        this.comentarios = new ArrayList<>();
        this.sorteos = new ArrayList<>();
    }

    public Trabajador() {

    }

    public Long getTrabajadorID() {
        return trabajadorID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public List<Sorteo> getSorteos() {
        return sorteos;
    }


    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setExperto(boolean experto) {
        this.experto=experto;
    }

    public boolean isExperto() {
        return experto;
    }

    public void setTrabajadorID(Long trabajadorID) {
        this.trabajadorID = trabajadorID;
    }

}
