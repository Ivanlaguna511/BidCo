package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.usuario.LoginDTO;
import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioUpdateDTO;

public interface UsuarioService {

    UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO usuarioCreateDTO);

    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO);

    UsuarioResponseDTO buscarUsuarioPorId(Long id);

    UsuarioResponseDTO buscarUsuarioPorNombreUsuario(String nombreUsuario);

    // Nuevo método para login con JWT
    String login(LoginDTO loginDTO);
}
