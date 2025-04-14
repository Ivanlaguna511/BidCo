package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.cometario.ComentarioCreateDTO;
import com.bidco.api_rest.dto.cometario.ComentarioResponseDTO;

public interface ComentarioService {

    ComentarioResponseDTO añadirComentario(ComentarioCreateDTO comentarioCreateDTO);
    ComentarioResponseDTO buscarComentarioPorId(Long id);
}
