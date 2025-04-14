package com.bidco.api_rest.mapper;

import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "usuarioID", source = "usuarioID")
    @Mapping(target = "nombreUsuario", source = "nombreUsuario")
    @Mapping(target = "correoElectronico", source = "correoElectronico")
    UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario);

    @Mapping(target = "nombreUsuario", source = "nombreUsuario")
    @Mapping(target = "correoElectronico", source = "correoElectronico")
    @Mapping(target = "contraseña", source = "contraseña")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "puntos", source = "puntos")
    @Mapping(target = "pais", source = "pais")
    @Mapping(target = "ciudad", source = "ciudad")
    @Mapping(target = "codigoPostal", source = "codigoPostal")
    @Mapping(target = "calle", source = "calle")
    @Mapping(target = "numeroPiso", source = "numeroPiso")
    @Mapping(target = "letraPiso", source = "letraPiso")
    Usuario usuarioCreateDTOToUsuario(UsuarioCreateDTO usuario);
}