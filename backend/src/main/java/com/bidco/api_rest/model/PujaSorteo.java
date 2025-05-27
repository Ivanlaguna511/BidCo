package com.bidco.api_rest.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "puja_sorteo")
public class PujaSorteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puja_sorteo_id")
    private Long pujaID;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private BigDecimal puntos;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario pujador;

    @ManyToOne
    @JoinColumn(name = "sorteo_id")
    private Sorteo sorteo;



    public PujaSorteo(Long pujaID, Sorteo sorteo, BigDecimal puntos, LocalDate fecha, Usuario pujador) {
        this.pujaID = pujaID;
        this.sorteo = sorteo;
        this.puntos = puntos;
        this.fecha = fecha;
        this.pujador = pujador;
    }

    public PujaSorteo() {

    }

    public Long getPujaID() {
        return pujaID;
    }

    public void setPujaID(Long pujaID) {
        this.pujaID = pujaID;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getPuntos() {
        return puntos;
    }

    public void setPuntos(BigDecimal puntos) {
        this.puntos = puntos;
    }

    public Sorteo getSorteo() {
        return sorteo;
    }

    public void setSorteo(Sorteo sorteo) {
        this.sorteo = sorteo;
    }

    public Usuario getPujador() {
        return pujador;
    }

    public void setPujador(Usuario pujador) {
        this.pujador = pujador;
    }
}
