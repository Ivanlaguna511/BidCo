package com.bidco.api_rest.mapper;

import com.bidco.api_rest.dto.cometario.ComentarioCreateDTO;
import com.bidco.api_rest.dto.cometario.ComentarioResponseDTO;
import com.bidco.api_rest.model.Comentario;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.model.Trabajador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    // Mapea ComentarioCreateDTO a Comentario
    @Mapping(target = "trabajador", source = "trabajadorId", qualifiedByName = "longToTrabajador")
    @Mapping(target = "subasta", source = "subastaID", qualifiedByName = "longToSubasta")
    @Mapping(target = "comentario", source = "comentario")
    Comentario comentarioCreateDTOToComentario(ComentarioCreateDTO dto);

    // Mapea Comentario a ComentarioResponseDTO
    @Mapping(source = "comentarioID", target = "comentarioID")
    @Mapping(source = "comentario", target = "comentario")
    @Mapping(source = "trabajador.trabajadorID", target = "trabajadorID")
    @Mapping(source = "subasta.subastaID", target = "subastaID")
    ComentarioResponseDTO comentarioToComentarioResponseDTO(Comentario comentario);

    // Conversión de Long a Trabajador
    @Named("longToTrabajador")
    default Trabajador longToTrabajador(Long trabajadorId) {
        if (trabajadorId == null) {
            return null;
        }
        Trabajador trabajador = new Trabajador();
        trabajador.setTrabajadorID(trabajadorId);
        return trabajador;
    }

    // Conversión de Long a Subasta
    @Named("longToSubasta")
    default Subasta longToSubasta(Long subastaId) {
        if (subastaId == null) {
            return null;
        }
        Subasta subasta = new Subasta();
        subasta.setSubastaID(subastaId);
        return subasta;
    }
}
