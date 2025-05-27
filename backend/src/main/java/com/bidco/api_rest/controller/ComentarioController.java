package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.cometario.ComentarioCreateDTO;
import com.bidco.api_rest.dto.cometario.ComentarioResponseDTO;
import com.bidco.api_rest.dto.sorteo.SorteoCreateDTO;
import com.bidco.api_rest.dto.sorteo.SorteoResponseDTO;
import com.bidco.api_rest.service.contract.ComentarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Endpoint para añadir un comentario
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioResponseDTO añadirComentario(@Valid @RequestBody ComentarioCreateDTO comentarioCreateDTO) {
        return comentarioService.añadirComentario(comentarioCreateDTO);
    }

    // Endpoint para buscar un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> buscarComentarioPorId(@PathVariable Long id) {
        try {
            ComentarioResponseDTO comentarioResponseDTO = comentarioService.buscarComentarioPorId(id);
            return new ResponseEntity<>(comentarioResponseDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Método para obtener los comentarios por subastaId
    @GetMapping("/subasta/{subastaId}")
    public ResponseEntity<List<ComentarioResponseDTO>> buscarComentariosPorSubastaId(@PathVariable Long subastaId) {
        System.out.println("uno");
        List<ComentarioResponseDTO> comentarios = comentarioService.buscarComentariosPorSubastaId(subastaId);
        return ResponseEntity.ok(comentarios);
    }
}