package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.service.contract.PujaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pujas")
public class PujaController {

    private final PujaService pujaService;

    @Autowired
    public PujaController(PujaService pujaService) {
        this.pujaService = pujaService;
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

}
