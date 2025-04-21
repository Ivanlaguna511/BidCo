package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.cometario.ComentarioCreateDTO;
import com.bidco.api_rest.dto.cometario.ComentarioResponseDTO;
import com.bidco.api_rest.mapper.ComentarioMapper;
import com.bidco.api_rest.model.Comentario;
import com.bidco.api_rest.repository.ComentarioRepository;
import com.bidco.api_rest.service.contract.ComentarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    @Override
    public ComentarioResponseDTO añadirComentario(ComentarioCreateDTO comentarioCreateDTO) {
        if(comentarioCreateDTO==null){
            throw new IllegalArgumentException("el comentario no puede ser nulo");
        }
        Comentario comentario = comentarioMapper.comentarioCreateDTOToComentario(comentarioCreateDTO);
        comentario = comentarioRepository.save(comentario);
        return comentarioMapper.comentarioToComentarioResponseDTO(comentario);
    }

    @Override
    public ComentarioResponseDTO buscarComentarioPorId(Long id) {
        
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("el comentario no existe"));

        
        return comentarioMapper.comentarioToComentarioResponseDTO(comentario);
    }
}
