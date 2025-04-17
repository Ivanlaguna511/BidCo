package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.pujasorteo.PujaSorteoResponseDTO;
import com.bidco.api_rest.dto.sorteo.SorteoCreateDTO;
import com.bidco.api_rest.dto.sorteo.SorteoResponseDTO;
import com.bidco.api_rest.service.contract.SorteoService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sorteos")
public class SorteoController {

    private final SorteoService sorteoService;

    @Autowired
    public SorteoController(SorteoService sorteoService) {
        this.sorteoService = sorteoService;
    }

    // Crear un nuevo sorteo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SorteoResponseDTO crearSorteo(@Valid @RequestBody SorteoCreateDTO sorteoCreateDTO) {
        return sorteoService.crearSorteo(sorteoCreateDTO);
    }

    // Buscar un sorteo por ID
    @GetMapping("/{id}")
    public SorteoResponseDTO buscarSorteoPorId(@PathVariable Long id) {
        return sorteoService.buscarSorteoPorId(id);
    }

    @PutMapping("/ganador/{id}")
    public PujaSorteoResponseDTO findGanadorSubasta(@PathVariable Long id) {
        return sorteoService.asignarGanadorYActualizarPrecioFinal(id);
    }

    // Obtener todos los sorteos
    @GetMapping
    public List<SorteoResponseDTO> obtenerTodosLosSorteos() {
        return sorteoService.obtenerTodosLosSorteos();
    }
}
