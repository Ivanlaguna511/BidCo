package com.bidco.api_rest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Long comentarioID;

    @Column(nullable = false)
    private String comentario;

    @ManyToOne()
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @ManyToOne()
    @JoinColumn(name = "subasta_id")
    private Subasta subasta;

    public Comentario(Long comentarioID, String comentario, Trabajador trabajador, Subasta subasta) {
        this.comentarioID = comentarioID;
        this.comentario = comentario;
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
}
