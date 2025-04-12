package com.bidco.api_rest.dto.cometario;

public class ComentarioResponseDTO {

    private Long comentarioID;
    private String comentario;
    private Long trabajadorID;
    private Long subastaID;


    // Constructor
    public ComentarioResponseDTO(Long comentarioID, String comentario, Long trabajadorID, Long subastaID) {
        this.comentarioID = comentarioID;
        this.comentario = comentario;
        this.trabajadorID = trabajadorID;
        this.subastaID = subastaID;

    }

    // Getters y Setters
    public Long getComentarioID() {
        return comentarioID;
    }

    public void setComentarioID(Long comentarioID) {
        this.comentarioID = comentarioID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getTrabajadorID() {
        return trabajadorID;
    }

    public void setTrabajadorID(Long trabajadorID) {
        this.trabajadorID = trabajadorID;
    }


    public Long getSubastaID() {
        return subastaID;
    }

    public void setSubastaID(Long subastaID) {
        this.subastaID = subastaID;
    }


}
