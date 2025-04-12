package com.bidco.api_rest.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "puja")
public class Puja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puja_id")
    private Long pujaID;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private BigDecimal importe;

    @Column(nullable = false)
    private boolean ganadora;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario pujador;

    @ManyToOne
    @JoinColumn(name = "subasta_id")
    private Subasta subasta;

    public Puja(Long pujaID, Subasta subasta, BigDecimal importe, LocalDate fecha, Usuario pujador) {
        this.pujaID = pujaID;
        this.subasta = subasta;
        this.importe = importe;
        this.fecha = fecha;
        this.pujador = pujador;
        this.ganadora = false;
    }

    public Puja() {

    }

    public Long getPujaID() {
        return pujaID;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public Usuario getPujador() {
        return pujador;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isGanadora() {
        return ganadora;
    }

    public void setPujaID(Long pujaID) {
        this.pujaID = pujaID;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public void setGanadora(boolean ganadora) {
        this.ganadora = ganadora;
    }

    public void setPujador(Usuario pujador) {
        this.pujador = pujador;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }
}
