package com.bidco.api_rest.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Long comentarioID;

    @Column(nullable = false)
    private String comentario;

    @Column(name = "precio_estimado",nullable = false)
    private BigDecimal precioEstimado;

    @ManyToOne()
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @ManyToOne()
    @JoinColumn(name = "subasta_id")
    private Subasta subasta;

    public Comentario(Long comentarioID, String comentario, BigDecimal precioEstimado,Trabajador trabajador, Subasta subasta) {
        this.comentarioID = comentarioID;
        this.comentario = comentario;
        this.precioEstimado = precioEstimado;
        this.trabajador = trabajador;
        this.subasta = subasta;
    }

    public Comentario() {
    }

    public Long getComentarioID() {
        return comentarioID;
    }

    public String getComentario() {
        return comentario;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setComentarioID(Long comentarioID) {
        this.comentarioID = comentarioID;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public BigDecimal getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(BigDecimal precioEstimado) {
        this.precioEstimado = precioEstimado;
    }
}