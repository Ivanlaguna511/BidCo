package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.cometario.ComentarioCreateDTO;
import com.bidco.api_rest.dto.cometario.ComentarioResponseDTO;

import java.util.List;

public interface ComentarioService {

    ComentarioResponseDTO añadirComentario(ComentarioCreateDTO comentarioCreateDTO);
    ComentarioResponseDTO buscarComentarioPorId(Long id);
    List<ComentarioResponseDTO> buscarComentariosPorSubastaId(Long id);
}