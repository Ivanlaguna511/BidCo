package com.bidco.api_rest.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioID;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false,name = "primer_apellido")
    private String primerApellido;
    @Column(nullable = false,name = "segundo_apellido")
    private String segundoApellido;
    @Column(unique = true, nullable = false,name = "nombre_usuario")
    private String nombreUsuario;
    @Column(unique = true,nullable = false,name = "correo_electronico")
    private String correoElectronico;
    @Column(nullable = false)
    private String contraseña;
    @Column(nullable = false)
    private BigDecimal saldo;
    @Column(nullable = false)
    private int puntos;
    @Column(nullable = false)
    private String pais;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String codigoPostal;
    @Column(nullable = false)
    private String calle;
    private int numeroPiso;
    private char letraPiso;


    @OneToMany(mappedBy = "pujador",fetch = FetchType.LAZY)
    private List<PujaSorteo> pujasSorteo;

    @OneToMany(mappedBy = "pujador",fetch = FetchType.LAZY)
    private List<Puja> pujas;

    @OneToMany(mappedBy = "creador",fetch = FetchType.LAZY)
    private List<Subasta> subastas;


    public Usuario(Long usuarioID, String nombre, String primerApellido, String segundoApellido, String nombreUsuario,
                   String correoElectronico, String contraseña, BigDecimal saldo, int puntos, String pais,
                   String ciudad, String codigoPostal, String calle, int numeroPiso, char letraPiso) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.saldo = saldo;
        this.puntos = puntos;
        this.pais = pais;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numeroPiso = numeroPiso;
        this.letraPiso = letraPiso;
        this.pujas = new ArrayList<>();
        this.subastas = new ArrayList<>();
        this.pujasSorteo = new ArrayList<>();
    }

    public Usuario() {

    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumeroPiso() {
        return numeroPiso;
    }

    public char getLetraPiso() {
        return letraPiso;
    }



    public List<Puja> getPujas() {
        return pujas;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
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

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumeroPiso(int numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public void setLetraPiso(char letraPiso) {
        this.letraPiso = letraPiso;
    }

    public void setPujas(List<Puja> pujas) {
        this.pujas = pujas;
    }

    public List<PujaSorteo> getPujasSorteo() {
        return pujasSorteo;
    }

    public void setPujasSorteo(List<PujaSorteo> pujasSorteo) {
        this.pujasSorteo = pujasSorteo;
    }

    public List<Subasta> getSubastas() {
        return subastas;
    }

    public void setSubastas(List<Subasta> subastas) {
        this.subastas = subastas;
    }
}
