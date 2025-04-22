package com.bidco.api_rest.mapper;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PujaMapper {



    @Mapping(target = "subasta", source = "subastaID", qualifiedByName = "longToSubasta")
    @Mapping(target = "pujador", source = "pujadorId", qualifiedByName = "longToUsuario")
    @Mapping(target = "importe", source = "importe")
    @Mapping(target = "fecha", source = "fecha")
    @Mapping(target = "ganadora", constant = "false") // Por defecto es false
    Puja pujaCreateDTOToPuja(PujaCreateDTO dto);

    // Conversión de Long a Subasta
    @Named("longToSubasta")
    default Subasta longToSubasta(Long subastaId) {
        if (subastaId == null) {
            return null; // Maneja como sea necesario si es nulo
        }
        Subasta subasta = new Subasta();
        subasta.setSubastaID(subastaId);
        return subasta;
    }

    // Conversión de Long a Usuario
    @Named("longToUsuario")
    default Usuario longToUsuario(Long usuarioId) {
        if (usuarioId == null) {
            return null; // Maneja como sea necesario si es nulo
        }
        Usuario usuario = new Usuario();
        usuario.setUsuarioID(usuarioId);
        return usuario;
    }

    @Mapping(source = "puja.pujaID", target = "pujaID")
    @Mapping(source = "puja.importe", target = "importe")
    @Mapping(source = "puja.fecha", target = "fecha")
    @Mapping(source = "puja.ganadora", target = "ganadora")
    @Mapping(source = "puja.subasta.subastaID", target = "subastaID")
    @Mapping(source = "puja.pujador", target = "pujadorID")
    PujaResponseDTO pujaToPujaResponseDTO(Puja puja);

}
