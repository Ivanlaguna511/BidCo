package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO usuarioCreateDTO);
    UsuarioResponseDTO actualizarUsuario(UsuarioCreateDTO usuarioCreateDTO);
    UsuarioResponseDTO buscarUsuarioPorId(Long id);
    UsuarioResponseDTO buscarUsuarioPorNombreUsuario(String nomreUsuario);
}
