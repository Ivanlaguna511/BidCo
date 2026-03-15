package com.bidco.api_rest.mapper;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy; // Importante para limpiar warnings

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PujaMapper {

    @Mapping(target = "subasta", source = "subastaID", qualifiedByName = "longToSubasta")
    @Mapping(target = "pujador", source = "pujadorId", qualifiedByName = "longToUsuario")
    @Mapping(target = "ganadora", constant = "false")
    Puja pujaCreateDTOToPuja(PujaCreateDTO dto);

    @Mapping(source = "puja.pujaID", target = "pujaID")
    @Mapping(source = "puja.subasta.subastaID", target = "subastaID")
    @Mapping(source = "puja.pujador", target = "pujadorID", qualifiedByName = "usuarioToLong")
    PujaResponseDTO pujaToPujaResponseDTO(Puja puja);

    // Métodos de apoyo (Helper methods)
    @Named("longToSubasta")
    default Subasta longToSubasta(Long subastaId) {
        if (subastaId == null) return null;
        Subasta subasta = new Subasta();
        subasta.setSubastaID(subastaId);
        return subasta;
    }

    @Named("longToUsuario")
    default Usuario longToUsuario(Long usuarioId) {
        if (usuarioId == null) return null;
        Usuario usuario = new Usuario();
        usuario.setUsuarioID(usuarioId);
        return usuario;
    }

    @Named("usuarioToLong")
    default Long usuarioToLong(Usuario usuario) {
        return (usuario != null) ? usuario.getUsuarioID() : null;
    }
}