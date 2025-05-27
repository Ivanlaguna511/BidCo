package com.bidco.api_rest.dto.usuario;

public class PrivacidadDTO {
    private Boolean privacidadAnonimoPujas;
    private Boolean privacidadEstadisticas;
    private Boolean privacidadPerfilVisible;

    // getters y setters
    public Boolean getPrivacidadAnonimoPujas() { return privacidadAnonimoPujas; }
    public void setPrivacidadAnonimoPujas(Boolean v) { this.privacidadAnonimoPujas = v; }

    public Boolean getPrivacidadEstadisticas() { return privacidadEstadisticas; }
    public void setPrivacidadEstadisticas(Boolean v) { this.privacidadEstadisticas = v; }

    public Boolean getPrivacidadPerfilVisible() { return privacidadPerfilVisible; }
    public void setPrivacidadPerfilVisible(Boolean v) { this.privacidadPerfilVisible = v; }
}