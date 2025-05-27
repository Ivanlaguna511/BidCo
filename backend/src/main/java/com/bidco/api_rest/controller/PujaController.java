package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.mapper.SubastaMapper;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.service.contract.PujaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pujas")
public class PujaController {

    private final PujaService pujaService;
    private final SubastaMapper subastaMapper;

    @Autowired
    public PujaController(PujaService pujaService, SubastaMapper subastaMapper) {
        this.pujaService = pujaService;
        this.subastaMapper = subastaMapper;
    }

    // Crear una nueva puja
    @PostMapping
    public PujaResponseDTO crearPuja(@Valid @RequestBody PujaCreateDTO pujaCreateDTO) {
        return pujaService.crearPuja(pujaCreateDTO);
    }

    // Buscar una puja por ID
    @GetMapping("/{id}")
    public PujaResponseDTO buscarPujaPorId(@PathVariable Long id) {
        return pujaService.buscarPujaPorId(id);
    }

    @GetMapping("/mayor/{subastaId}")
    public PujaResponseDTO obtenerPujaMasAlta(@PathVariable Long subastaId) {
        return pujaService.obtenerPujaMasAlta(subastaId);
    }

    @GetMapping("/subastas-usuario/{usuarioId}")
    public List<SubastaResponseDTO> obtenerSubastasPorUsuario(@PathVariable Long usuarioId) {
        return pujaService.obtenerSubastasPorUsuarioId(usuarioId).stream().map(subastaMapper::subastaToSubastaResponseDTO).toList();
    }
}
