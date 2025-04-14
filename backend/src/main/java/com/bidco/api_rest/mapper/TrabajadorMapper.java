package com.bidco.api_rest.mapper;

import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.dto.trabajador.TrabajadorCreateDTO;
import com.bidco.api_rest.dto.trabajador.TrabajadorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TrabajadorMapper {



    // Mapear Trabajador a TrabajadorResponseDTO
    @Mapping(source = "trabajadorID", target = "trabajadorID")
    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "correoElectronico", target = "correoElectronico")
    TrabajadorResponseDTO trabajadorToTrabajadorResponseDTO(Trabajador trabajador);

    // Mapear TrabajadorCreateDTO a Trabajador
    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "correoElectronico", target = "correoElectronico")
    @Mapping(source = "contraseña", target = "contraseña")
    @Mapping(source = "experto", target = "experto")
    Trabajador trabajadorCreateDTOToTrabajador(TrabajadorCreateDTO trabajadorCreateDTO);
}