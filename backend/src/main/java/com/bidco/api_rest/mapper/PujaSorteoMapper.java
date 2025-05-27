package com.bidco.api_rest.mapper;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.pujasorteo.PujaSorteoCreateDTO;
import com.bidco.api_rest.dto.pujasorteo.PujaSorteoResponseDTO;
import com.bidco.api_rest.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PujaSorteoMapper {



    @Mapping(target = "sorteo", source = "sorteoId", qualifiedByName = "longToSorteo")
    @Mapping(target = "pujador", source = "pujadorId", qualifiedByName = "longToUsuario")
    @Mapping(target = "puntos", source = "puntos")
    @Mapping(target = "fecha", source = "fecha")
    PujaSorteo pujaSorteoCreateDTOToPujaSorteo(PujaSorteoCreateDTO dto);

    // Conversión de Long a Subasta
    @Named("longToSorteo")
    default Sorteo longToSorteo(Long sorteoId) {
        if (sorteoId == null) {
            return null; // Maneja como sea necesario si es nulo
        }
        Sorteo sorteo = new Sorteo();
        sorteo.setSorteoID(sorteoId);
        return sorteo;
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
    @Mapping(source = "puja.puntos", target = "puntos")
    @Mapping(source = "puja.fecha", target = "fecha")
    @Mapping(source = "puja.sorteo.sorteoID", target = "sorteoID")
    @Mapping(source = "puja.pujador.usuarioID", target = "pujadorID")
    PujaSorteoResponseDTO pujaSorteoToPujaSorteoResponseDTO(PujaSorteo puja);

}
